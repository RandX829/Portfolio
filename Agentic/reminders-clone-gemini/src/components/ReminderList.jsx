import React, { useState } from 'react';
import { useStore } from '../store/useStore';
import { Plus, Check, MoreHorizontal, Trash2 } from 'lucide-react';
import dayjs from 'dayjs';
import { clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

const cn = (...inputs) => twMerge(clsx(inputs));

const ReminderList = () => {
    const {
        lists,
        reminders,
        selectedListId,
        addReminder,
        updateReminder,
        deleteReminder,
        toggleReminder,
        deleteList,
        setSelectedListId,
        searchQuery
    } = useStore();

    const [newReminderTitle, setNewReminderTitle] = useState('');

    const selectedList = lists.find(l => l.id === selectedListId);

    const filteredReminders = reminders.filter(r => {
        const matchesSearch = r.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
            (r.notes && r.notes.toLowerCase().includes(searchQuery.toLowerCase()));

        if (searchQuery) return matchesSearch;

        if (selectedListId === 'all') return true;
        if (selectedListId === 'completed') return r.isCompleted;
        if (selectedListId === 'today') {
            const today = dayjs().format('YYYY-MM-DD');
            return r.dueDate && dayjs(r.dueDate).isSame(today, 'day');
        }
        if (selectedListId === 'scheduled') {
            return !!r.dueDate;
        }

        return r.listId === selectedListId;
    });

    const handleAddReminder = (e) => {
        if (e.key === 'Enter' && newReminderTitle.trim()) {
            addReminder({
                title: newReminderTitle,
                listId: selectedListId === 'all' || selectedListId === 'today' ? 'personal' : selectedListId,
            });
            setNewReminderTitle('');
        }
    };

    if (!selectedList) return <div className="flex-1 p-8">Select a list</div>;

    return (
        <div className="flex-1 flex flex-col h-full bg-white">
            {/* Header */}
            <div className="p-8 pb-4 flex justify-between items-center">
                <h1 className={cn(
                    "text-3xl font-bold flex items-center gap-2",
                    selectedList.color === 'blue' ? "text-apple-blue" :
                        selectedList.color === 'orange' ? "text-apple-orange" :
                            selectedList.color === 'yellow' ? "text-apple-yellow" :
                                selectedList.color === 'red' ? "text-apple-red" :
                                    selectedList.color === 'green' ? "text-apple-green" :
                                        selectedList.color === 'purple' ? "text-apple-purple" :
                                            selectedList.color === 'pink' ? "text-apple-pink" :
                                                "text-apple-blue"
                )}>
                    {selectedList.name}
                </h1>
                <div className="flex gap-2">
                    {!selectedList.isSmart && (
                        <button
                            onClick={() => {
                                if (confirm(`Delete list "${selectedList.name}"?`)) {
                                    deleteList(selectedList.id);
                                }
                            }}
                            className="text-apple-red hover:bg-apple-red/10 p-2 rounded-full transition-colors"
                        >
                            <Trash2 size={20} />
                        </button>
                    )}
                    <button className="text-apple-blue hover:bg-apple-blue/10 p-2 rounded-full transition-colors">
                        <MoreHorizontal size={20} />
                    </button>
                </div>
            </div>

            {/* List */}
            <div className="flex-1 overflow-y-auto px-8">
                <div className="space-y-0.5 divide-y divide-apple-gray-5">
                    {filteredReminders.map((reminder) => (
                        <div key={reminder.id} className="flex items-start py-3 group">
                            <button
                                onClick={() => toggleReminder(reminder.id)}
                                className={cn(
                                    "mt-0.5 w-6 h-6 rounded-full border-2 flex items-center justify-center transition-all shrink-0",
                                    reminder.isCompleted
                                        ? "bg-apple-blue border-apple-blue text-white"
                                        : "border-apple-gray-4 hover:border-apple-blue"
                                )}
                            >
                                {reminder.isCompleted && <Check size={14} strokeWidth={3} />}
                            </button>
                            <div className="ml-3 flex-1 min-w-0">
                                <div className="flex items-center gap-2">
                                    <select
                                        className={cn(
                                            "text-xs font-bold uppercase tracking-wider rounded px-1",
                                            reminder.priority === 'high' ? "text-apple-red bg-apple-red/10" :
                                                reminder.priority === 'medium' ? "text-apple-orange bg-apple-orange/10" :
                                                    reminder.priority === 'low' ? "text-apple-blue bg-apple-blue/10" :
                                                        "text-apple-gray bg-apple-gray/10"
                                        )}
                                        value={reminder.priority || 'none'}
                                        onChange={(e) => updateReminder(reminder.id, { priority: e.target.value })}
                                    >
                                        <option value="none">None</option>
                                        <option value="low">Low</option>
                                        <option value="medium">Medium</option>
                                        <option value="high">High</option>
                                    </select>
                                    <input
                                        className={cn(
                                            "flex-1 text-base bg-transparent focus:outline-none transition-all",
                                            reminder.isCompleted ? "text-apple-gray line-through" : "text-black font-medium"
                                        )}
                                        value={reminder.title}
                                        onChange={(e) => updateReminder(reminder.id, { title: e.target.value })}
                                    />
                                </div>
                                <input
                                    className="w-full text-sm text-apple-gray bg-transparent focus:outline-none placeholder:italic"
                                    placeholder="Add notes..."
                                    value={reminder.notes || ''}
                                    onChange={(e) => updateReminder(reminder.id, { notes: e.target.value })}
                                />
                                <div className="flex items-center gap-2 mt-1">
                                    <input
                                        type="date"
                                        className="text-xs text-apple-blue font-medium bg-apple-blue/5 border-none rounded focus:ring-0 px-1 py-0.5"
                                        value={reminder.dueDate || ''}
                                        onChange={(e) => updateReminder(reminder.id, { dueDate: e.target.value })}
                                    />
                                </div>
                            </div>
                            <button
                                onClick={() => deleteReminder(reminder.id)}
                                className="opacity-0 group-hover:opacity-100 text-apple-gray-3 hover:text-apple-red transition-all p-1"
                            >
                                <Trash2 size={16} />
                            </button>
                        </div>
                    ))}

                    {/* New Reminder Input */}
                    <div className="flex items-center py-3">
                        <div className="w-6 h-6 rounded-full border-2 border-apple-gray-3 flex items-center justify-center text-apple-gray-3">
                            <Plus size={14} />
                        </div>
                        <input
                            type="text"
                            placeholder="New Reminder"
                            className="ml-3 flex-1 text-base focus:outline-none placeholder:text-apple-gray-3"
                            value={newReminderTitle}
                            onChange={(e) => setNewReminderTitle(e.target.value)}
                            onKeyDown={handleAddReminder}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReminderList;
