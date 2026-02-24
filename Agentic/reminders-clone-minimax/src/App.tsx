import { useState, useMemo } from 'react';
import dayjs from 'dayjs';
import isToday from 'dayjs/plugin/isToday';
import isTomorrow from 'dayjs/plugin/isTomorrow';
import isYesterday from 'dayjs/plugin/isYesterday';
import {
  Check,
  Flag,
  Plus,
  ChevronDown,
  ChevronRight,
  Search,
  MoreHorizontal,
  Pencil,
  Trash2,
  X,
} from 'lucide-react';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Checkbox } from '@/components/ui/checkbox';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from '@/components/ui/dialog';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import { useStore } from '@/store/useStore';
import type { List, Reminder, ViewType } from '@/types';

dayjs.extend(isToday);
dayjs.extend(isTomorrow);
dayjs.extend(isYesterday);

const LIST_COLORS = [
  { name: 'Blue', value: '#007AFF' },
  { name: 'Purple', value: '#AF52DE' },
  { name: 'Pink', value: '#FF2D55' },
  { name: 'Red', value: '#FF3B30' },
  { name: 'Orange', value: '#FF9500' },
  { name: 'Yellow', value: '#FFCC00' },
  { name: 'Green', value: '#34C759' },
  { name: 'Teal', value: '#5AC8FA' },
  { name: 'Indigo', value: '#5856D6' },
  { name: 'Gray', value: '#8E8E93' },
];

function getPriorityColor(priority: Reminder['priority']) {
  switch (priority) {
    case 'high':
      return 'bg-ios-red';
    case 'medium':
      return 'bg-ios-yellow';
    case 'low':
      return 'bg-ios-orange';
    default:
      return 'bg-ios-gray/30';
  }
}

function getDueDateBadge(reminder: Reminder) {
  if (!reminder.dueDate) return null;

  const dueDate = dayjs(reminder.dueDate);
  const isOverdue = dueDate.isBefore(dayjs(), 'day');
  const isOverdueToday = isOverdue && dueDate.isToday();

  let badgeClass = 'bg-ios-gray/20 text-ios-gray';
  let label = '';

  if (isOverdueToday) {
    badgeClass = 'bg-ios-red text-white';
    label = 'Overdue';
  } else if (dueDate.isToday()) {
    badgeClass = 'bg-ios-yellow text-black';
    label = 'Today';
  } else if (dueDate.isTomorrow()) {
    badgeClass = 'bg-ios-gray/20 text-ios-gray';
    label = 'Tomorrow';
  } else if (dueDate.isBefore(dayjs().add(7, 'day'))) {
    badgeClass = 'bg-ios-gray/20 text-ios-gray';
    label = dueDate.format('ddd');
  } else {
    badgeClass = 'bg-ios-gray/20 text-ios-gray';
    label = dueDate.format('MMM D');
  }

  return { class: badgeClass, label, date: dueDate };
}

export default function App() {
  const {
    lists,
    reminders,
    currentView,
    selectedListId,
    searchQuery,
    setCurrentView,
    setSelectedListId,
    setSearchQuery,
    addList,
    updateList,
    deleteList,
    addReminder,
    updateReminder,
    deleteReminder,
    toggleComplete,
    toggleFlag,
  } = useStore();

  const [newListOpen, setNewListOpen] = useState(false);
  const [newListName, setNewListName] = useState('');
  const [newListColor, setNewListColor] = useState(LIST_COLORS[0].value);
  const [editListOpen, setEditListOpen] = useState(false);
  const [editingList, setEditingList] = useState<List | null>(null);
  const [editListName, setEditListName] = useState('');
  const [editListColor, setEditListColor] = useState('');
  const [quickAddTitle, setQuickAddTitle] = useState('');
  const [editingReminder, setEditingReminder] = useState<Reminder | null>(null);
  const [showCompleted, setShowCompleted] = useState(false);

  const filteredReminders = useMemo(() => {
    let filtered = reminders;

    if (searchQuery) {
      const q = searchQuery.toLowerCase();
      filtered = filtered.filter(
        (r) =>
          r.title.toLowerCase().includes(q) ||
          r.notes?.toLowerCase().includes(q)
      );
    } else if (selectedListId) {
      filtered = filtered.filter((r) => r.listId === selectedListId);
    } else {
      switch (currentView) {
        case 'today':
          filtered = reminders.filter((r) => {
            if (r.completed) return false;
            if (!r.dueDate) return false;
            const due = dayjs(r.dueDate);
            return due.isToday() || due.isBefore(dayjs(), 'day');
          });
          break;
        case 'scheduled':
          filtered = reminders.filter((r) => {
            if (r.completed) return false;
            if (!r.dueDate) return false;
            return dayjs(r.dueDate).isAfter(dayjs(), 'day');
          });
          break;
        case 'flagged':
          filtered = reminders.filter((r) => r.flagged && !r.completed);
          break;
        case 'all':
        default:
          break;
      }
    }

    const active = filtered.filter((r) => !r.completed);
    const completed = filtered.filter((r) => r.completed);

    return { active, completed };
  }, [reminders, currentView, selectedListId, searchQuery]);

  const viewCounts = useMemo(() => {
    const today = reminders.filter((r) => {
      if (r.completed || !r.dueDate) return false;
      const due = dayjs(r.dueDate);
      return due.isToday() || due.isBefore(dayjs(), 'day');
    }).length;

    const scheduled = reminders.filter((r) => {
      if (r.completed || !r.dueDate) return false;
      return dayjs(r.dueDate).isAfter(dayjs(), 'day');
    }).length;

    const flagged = reminders.filter((r) => r.flagged && !r.completed).length;

    return { today, scheduled, all: reminders.length, flagged };
  }, [reminders]);

  const getListForReminder = (listId: string) =>
    lists.find((l) => l.id === listId);

  const handleAddList = () => {
    if (newListName.trim()) {
      addList(newListName.trim(), newListColor);
      setNewListName('');
      setNewListColor(LIST_COLORS[0].value);
      setNewListOpen(false);
    }
  };

  const handleEditList = () => {
    if (editingList && editListName.trim()) {
      updateList(editingList.id, {
        name: editListName.trim(),
        color: editListColor,
      });
      setEditListOpen(false);
      setEditingList(null);
    }
  };

  const handleDeleteList = (list: List) => {
    if (confirm(`Delete "${list.name}" and all its reminders?`)) {
      deleteList(list.id);
    }
  };

  const handleAddReminder = (e: React.FormEvent) => {
    e.preventDefault();
    const targetListId = selectedListId || lists[0]?.id;
    if (quickAddTitle.trim() && targetListId) {
      addReminder(targetListId, quickAddTitle.trim());
      setQuickAddTitle('');
    }
  };

  const handleSaveReminder = () => {
    if (editingReminder) {
      updateReminder(editingReminder.id, {
        title: editingReminder.title,
        notes: editingReminder.notes,
        dueDate: editingReminder.dueDate,
        dueTime: editingReminder.dueTime,
        priority: editingReminder.priority,
        flagged: editingReminder.flagged,
      });
      setEditingReminder(null);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Escape') {
      setSearchQuery('');
    }
  };

  const ViewPill = ({
    view,
    label,
    count,
  }: {
    view: ViewType;
    label: string;
    count: number;
  }) => (
    <button
      onClick={() => setCurrentView(view)}
      className={cn(
        'flex items-center gap-1.5 px-3 py-1.5 rounded-full text-sm font-medium transition-all',
        currentView === view && selectedListId === null
          ? 'bg-ios-blue text-white shadow-sm'
          : 'bg-white text-ios-gray hover:bg-white/80'
      )}
    >
      <span>{label}</span>
      {count > 0 && (
        <span
          className={cn(
            'text-xs px-1.5 py-0.5 rounded-full',
            currentView === view && selectedListId === null
              ? 'bg-white/20 text-white'
              : 'bg-ios-gray/20 text-ios-gray'
          )}
        >
          {count}
        </span>
      )}
    </button>
  );

  return (
    <div className="flex h-screen bg-[#F2F2F7]" onKeyDown={handleKeyDown}>
      {/* Sidebar */}
      <aside className="w-64 bg-[#F2F2F7] border-r border-gray/10 flex flex-col">
        <div className="p-4">
          <h2 className="text-xs font-semibold text-ios-gray uppercase tracking-wider mb-2">
            My Lists
          </h2>
          <div className="space-y-0.5">
            {lists
              .slice()
              .sort((a, b) => a.order - b.order)
              .map((list) => (
                <div
                  key={list.id}
                  onClick={() => setSelectedListId(list.id)}
                  className={cn(
                    'flex items-center gap-2 px-3 py-2 rounded-ios cursor-pointer transition-colors',
                    selectedListId === list.id
                      ? 'bg-white shadow-sm'
                      : 'hover:bg-white/50'
                  )}
                >
                  <div
                    className="w-3 h-3 rounded-full"
                    style={{ backgroundColor: list.color }}
                  />
                  <span className="flex-1 text-sm font-medium truncate">
                    {list.name}
                  </span>
                  <DropdownMenu>
                    <DropdownMenuTrigger asChild>
                      <button
                        onClick={(e) => e.stopPropagation()}
                        className="p-1 hover:bg-gray/10 rounded opacity-0 group-hover:opacity-100"
                      >
                        <MoreHorizontal className="w-4 h-4 text-ios-gray" />
                      </button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent align="start">
                      <DropdownMenuItem
                        onClick={() => {
                          setEditingList(list);
                          setEditListName(list.name);
                          setEditListColor(list.color);
                          setEditListOpen(true);
                        }}
                      >
                        <Pencil className="w-4 h-4 mr-2" />
                        Edit
                      </DropdownMenuItem>
                      <DropdownMenuItem
                        onClick={() => handleDeleteList(list)}
                        className="text-ios-red"
                      >
                        <Trash2 className="w-4 h-4 mr-2" />
                        Delete
                      </DropdownMenuItem>
                    </DropdownMenuContent>
                  </DropdownMenu>
                </div>
              ))}
          </div>
          <button
            onClick={() => setNewListOpen(true)}
            className="flex items-center gap-2 w-full px-3 py-2 mt-2 text-ios-blue font-medium text-sm hover:bg-ios-blue/10 rounded-ios transition-colors"
          >
            <Plus className="w-4 h-4" />
            New List
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
        <header className="px-6 pt-6 pb-4">
          <div className="flex items-center justify-between mb-4">
            <div className="flex items-center gap-4">
              <h1 className="text-2xl font-semibold text-gray-900">
                {selectedListId
                  ? lists.find((l) => l.id === selectedListId)?.name
                  : currentView.charAt(0).toUpperCase() + currentView.slice(1)}
              </h1>
            </div>
            <div className="relative">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-ios-gray" />
              <Input
                placeholder="Search"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="pl-9 w-48 bg-white"
              />
              {searchQuery && (
                <button
                  onClick={() => setSearchQuery('')}
                  className="absolute right-3 top-1/2 -translate-y-1/2"
                >
                  <X className="w-4 h-4 text-ios-gray" />
                </button>
              )}
            </div>
          </div>

          {/* View Pills */}
          {!searchQuery && (
            <div className="flex gap-2">
              <ViewPill view="today" label="Today" count={viewCounts.today} />
              <ViewPill view="scheduled" label="Scheduled" count={viewCounts.scheduled} />
              <ViewPill view="all" label="All" count={viewCounts.all} />
              <ViewPill view="flagged" label="Flagged" count={viewCounts.flagged} />
            </div>
          )}
        </header>

        {/* Reminder List */}
        <div className="flex-1 overflow-y-auto px-6 pb-6">
          {/* Quick Add */}
          <form onSubmit={handleAddReminder} className="mb-4">
            <div className="flex items-center gap-3 bg-white rounded-ios-lg p-3 shadow-sm">
              <div className="w-6 h-6 rounded-full border-2 border-ios-gray/30" />
              <Input
                placeholder="New Reminder"
                value={quickAddTitle}
                onChange={(e) => setQuickAddTitle(e.target.value)}
                className="border-0 shadow-none focus-visible:ring-0 text-base"
              />
            </div>
          </form>

          {/* Active Reminders */}
          <div className="space-y-0.5">
            {filteredReminders.active.map((reminder) => {
              const list = getListForReminder(reminder.listId);
              const dueBadge = getDueDateBadge(reminder);

              return (
                <div
                  key={reminder.id}
                  onClick={() => setEditingReminder(reminder)}
                  className="flex items-center gap-3 bg-white rounded-ios-lg p-3 shadow-sm cursor-pointer hover:bg-gray-50/50 transition-colors group"
                >
                  <div onClick={(e) => { e.stopPropagation(); toggleComplete(reminder.id); }}>
                    <Checkbox
                      checked={reminder.completed}
                      className={cn(
                        'border-2',
                        reminder.completed && list && `bg-[${list.color}] border-[${list.color}]`
                      )}
                      style={{
                        borderColor: reminder.completed ? list?.color : undefined,
                        backgroundColor: reminder.completed ? list?.color : undefined,
                      }}
                    />
                  </div>
                  <div className="flex-1 min-w-0">
                    <div className="flex items-center gap-2">
                      <span
                        className={cn(
                          'text-base truncate',
                          reminder.completed && 'line-through text-ios-gray'
                        )}
                      >
                        {reminder.title}
                      </span>
                      {reminder.priority !== 'none' && (
                        <div className={cn('w-2 h-2 rounded-full', getPriorityColor(reminder.priority))} />
                      )}
                    </div>
                    <div className="flex items-center gap-2 mt-0.5">
                      {dueBadge && (
                        <span
                          className={cn(
                            'text-xs px-1.5 py-0.5 rounded',
                            dueBadge.class
                          )}
                        >
                          {dueBadge.label}
                          {dueBadge.date && dueBadge.date.isToday() && reminder.dueTime && (
                            <span className="ml-1">{reminder.dueTime}</span>
                          )}
                        </span>
                      )}
                      {list && !selectedListId && (
                        <span className="text-xs text-ios-gray flex items-center gap-1">
                          <div className="w-2 h-2 rounded-full" style={{ backgroundColor: list.color }} />
                          {list.name}
                        </span>
                      )}
                    </div>
                  </div>
                  <button
                    onClick={(e) => { e.stopPropagation(); toggleFlag(reminder.id); }}
                    className={cn(
                      'p-1.5 rounded-full transition-colors',
                      reminder.flagged
                        ? 'text-ios-orange bg-ios-orange/10'
                        : 'text-ios-gray/40 hover:text-ios-orange hover:bg-ios-orange/10 opacity-0 group-hover:opacity-100'
                    )}
                  >
                    <Flag className={cn('w-4 h-4', reminder.flagged && 'fill-current')} />
                  </button>
                </div>
              );
            })}
          </div>

          {/* Completed Section */}
          {filteredReminders.completed.length > 0 && (
            <div className="mt-4">
              <button
                onClick={() => setShowCompleted(!showCompleted)}
                className="flex items-center gap-2 text-ios-gray font-medium text-sm py-2"
              >
                {showCompleted ? (
                  <ChevronDown className="w-4 h-4" />
                ) : (
                  <ChevronRight className="w-4 h-4" />
                )}
                Completed ({filteredReminders.completed.length})
              </button>
              {showCompleted && (
                <div className="space-y-0.5 mt-2">
                  {filteredReminders.completed.map((reminder) => {
                    const list = getListForReminder(reminder.listId);
                    return (
                      <div
                        key={reminder.id}
                        onClick={() => setEditingReminder(reminder)}
                        className="flex items-center gap-3 bg-white rounded-ios-lg p-3 shadow-sm cursor-pointer hover:bg-gray-50/50 transition-colors group opacity-70"
                      >
                        <div onClick={(e) => { e.stopPropagation(); toggleComplete(reminder.id); }}>
                          <Checkbox
                            checked={true}
                            className="border-2"
                            style={{
                              borderColor: list?.color,
                              backgroundColor: list?.color,
                            }}
                          />
                        </div>
                        <div className="flex-1 min-w-0">
                          <span className="text-base line-through text-ios-gray truncate block">
                            {reminder.title}
                          </span>
                          {reminder.completedAt && (
                            <span className="text-xs text-ios-gray">
                              {dayjs(reminder.completedAt).format('MMM D')}
                            </span>
                          )}
                        </div>
                        <button
                          onClick={(e) => { e.stopPropagation(); deleteReminder(reminder.id); }}
                          className="p-1.5 rounded-full text-ios-gray/40 hover:text-ios-red hover:bg-ios-red/10 opacity-0 group-hover:opacity-100 transition-all"
                        >
                          <Trash2 className="w-4 h-4" />
                        </button>
                      </div>
                    );
                  })}
                </div>
              )}
            </div>
          )}

          {/* Empty State */}
          {filteredReminders.active.length === 0 &&
            filteredReminders.completed.length === 0 && (
              <div className="flex flex-col items-center justify-center py-16 text-ios-gray">
                <div className="w-16 h-16 bg-ios-gray/10 rounded-full flex items-center justify-center mb-4">
                  <Check className="w-8 h-8" />
                </div>
                <p className="font-medium">No Reminders</p>
                <p className="text-sm">Tap above to add a reminder</p>
              </div>
            )}
        </div>
      </main>

      {/* New List Dialog */}
      <Dialog open={newListOpen} onOpenChange={setNewListOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>New List</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <Input
              placeholder="List Name"
              value={newListName}
              onChange={(e) => setNewListName(e.target.value)}
              onKeyDown={(e) => e.key === 'Enter' && handleAddList()}
            />
            <div className="flex flex-wrap gap-2">
              {LIST_COLORS.map((color) => (
                <button
                  key={color.value}
                  onClick={() => setNewListColor(color.value)}
                  className={cn(
                    'w-8 h-8 rounded-full transition-transform',
                    newListColor === color.value && 'scale-110 ring-2 ring-offset-2 ring-gray-400'
                  )}
                  style={{ backgroundColor: color.value }}
                />
              ))}
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setNewListOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleAddList}>Add</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      {/* Edit List Dialog */}
      <Dialog open={editListOpen} onOpenChange={setEditListOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Edit List</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <Input
              placeholder="List Name"
              value={editListName}
              onChange={(e) => setEditListName(e.target.value)}
            />
            <div className="flex flex-wrap gap-2">
              {LIST_COLORS.map((color) => (
                <button
                  key={color.value}
                  onClick={() => setEditListColor(color.value)}
                  className={cn(
                    'w-8 h-8 rounded-full transition-transform',
                    editListColor === color.value && 'scale-110 ring-2 ring-offset-2 ring-gray-400'
                  )}
                  style={{ backgroundColor: color.value }}
                />
              ))}
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setEditListOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleEditList}>Save</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      {/* Edit Reminder Dialog */}
      <Dialog open={!!editingReminder} onOpenChange={() => setEditingReminder(null)}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Edit Reminder</DialogTitle>
          </DialogHeader>
          {editingReminder && (
            <div className="space-y-4 py-4">
              <Input
                placeholder="Title"
                value={editingReminder.title}
                onChange={(e) =>
                  setEditingReminder({ ...editingReminder, title: e.target.value })
                }
              />
              <textarea
                placeholder="Notes"
                value={editingReminder.notes || ''}
                onChange={(e) =>
                  setEditingReminder({ ...editingReminder, notes: e.target.value })
                }
                className="w-full min-h-[80px] rounded-ios border border-input bg-background px-3 py-2 text-sm"
              />
              <div className="flex gap-4">
                <div className="flex-1">
                  <label className="text-xs text-ios-gray mb-1 block">Due Date</label>
                  <Input
                    type="date"
                    value={editingReminder.dueDate || ''}
                    onChange={(e) =>
                      setEditingReminder({ ...editingReminder, dueDate: e.target.value })
                    }
                  />
                </div>
                <div className="flex-1">
                  <label className="text-xs text-ios-gray mb-1 block">Time</label>
                  <Input
                    type="time"
                    value={editingReminder.dueTime || ''}
                    onChange={(e) =>
                      setEditingReminder({ ...editingReminder, dueTime: e.target.value })
                    }
                  />
                </div>
              </div>
              <div>
                <label className="text-xs text-ios-gray mb-2 block">Priority</label>
                <div className="flex gap-2">
                  {(['none', 'low', 'medium', 'high'] as const).map((p) => (
                    <button
                      key={p}
                      onClick={() => setEditingReminder({ ...editingReminder, priority: p })}
                      className={cn(
                        'flex-1 py-2 rounded-ios text-sm font-medium transition-colors',
                        editingReminder.priority === p
                          ? p === 'high'
                            ? 'bg-ios-red text-white'
                            : p === 'medium'
                            ? 'bg-ios-yellow text-black'
                            : p === 'low'
                            ? 'bg-ios-orange text-white'
                            : 'bg-ios-gray/20 text-ios-gray'
                          : 'bg-gray/10 text-ios-gray hover:bg-gray/20'
                      )}
                    >
                      {p.charAt(0).toUpperCase() + p.slice(1)}
                    </button>
                  ))}
                </div>
              </div>
              <div className="flex items-center justify-between">
                <span className="text-sm font-medium">Flag</span>
                <button
                  onClick={() =>
                    setEditingReminder({ ...editingReminder, flagged: !editingReminder.flagged })
                  }
                  className={cn(
                    'p-2 rounded-full transition-colors',
                    editingReminder.flagged
                      ? 'text-ios-orange bg-ios-orange/10'
                      : 'text-ios-gray/40 bg-gray/10'
                  )}
                >
                  <Flag className={cn('w-5 h-5', editingReminder.flagged && 'fill-current')} />
                </button>
              </div>
            </div>
          )}
          <DialogFooter className="flex justify-between">
            <Button
              variant="destructive"
              onClick={() => {
                if (editingReminder) {
                  deleteReminder(editingReminder.id);
                  setEditingReminder(null);
                }
              }}
            >
              Delete
            </Button>
            <div className="flex gap-2">
              <Button variant="outline" onClick={() => setEditingReminder(null)}>
                Cancel
              </Button>
              <Button onClick={handleSaveReminder}>Save</Button>
            </div>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
