import { create } from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';
import type { List, Reminder, ViewType } from '@/types';
import { loadData } from '@/lib/storage';

function generateId(): string {
  return Math.random().toString(36).substring(2, 15) + Date.now().toString(36);
}

const DEFAULT_LISTS: List[] = [
  { id: 'personal', name: 'Personal', color: '#007AFF', order: 0, createdAt: new Date().toISOString() },
  { id: 'work', name: 'Work', color: '#FF9500', order: 1, createdAt: new Date().toISOString() },
  { id: 'groceries', name: 'Groceries', color: '#34C759', order: 2, createdAt: new Date().toISOString() },
];

const DEFAULT_REMINDERS: Reminder[] = [
  {
    id: '1',
    listId: 'personal',
    title: 'Call mom',
    notes: 'Ask about weekend plans',
    dueDate: new Date().toISOString().split('T')[0],
    dueTime: '15:00',
    priority: 'high',
    flagged: true,
    completed: false,
    createdAt: new Date().toISOString(),
  },
  {
    id: '2',
    listId: 'groceries',
    title: 'Buy milk',
    dueDate: new Date(Date.now() + 86400000).toISOString().split('T')[0],
    priority: 'medium',
    flagged: false,
    completed: false,
    createdAt: new Date().toISOString(),
  },
  {
    id: '3',
    listId: 'work',
    title: 'Submit quarterly report',
    notes: 'Include all regional sales data',
    dueDate: new Date(Date.now() + 3 * 86400000).toISOString().split('T')[0],
    priority: 'high',
    flagged: false,
    completed: false,
    createdAt: new Date().toISOString(),
  },
  {
    id: '4',
    listId: 'personal',
    title: 'Gym workout',
    priority: 'low',
    flagged: false,
    completed: false,
    createdAt: new Date().toISOString(),
  },
  {
    id: '5',
    listId: 'work',
    title: 'Team meeting',
    dueDate: new Date(Date.now() + 2 * 86400000).toISOString().split('T')[0],
    dueTime: '10:00',
    priority: 'medium',
    flagged: false,
    completed: false,
    createdAt: new Date().toISOString(),
  },
];

interface AppStore {
  lists: List[];
  reminders: Reminder[];
  currentView: ViewType;
  selectedListId: string | null;
  searchQuery: string;
  isLoading: boolean;
  
  setCurrentView: (view: ViewType) => void;
  setSelectedListId: (id: string | null) => void;
  setSearchQuery: (query: string) => void;
  
  addList: (name: string, color: string) => void;
  updateList: (id: string, updates: Partial<List>) => void;
  deleteList: (id: string) => void;
  reorderLists: (lists: List[]) => void;
  
  addReminder: (listId: string, title: string) => void;
  updateReminder: (id: string, updates: Partial<Reminder>) => void;
  deleteReminder: (id: string) => void;
  toggleComplete: (id: string) => void;
  toggleFlag: (id: string) => void;
  
  loadFromStorage: () => Promise<void>;
}

export const useStore = create<AppStore>()(
  persist(
    (set, get) => ({
      lists: DEFAULT_LISTS,
      reminders: DEFAULT_REMINDERS,
      currentView: 'today',
      selectedListId: null,
      searchQuery: '',
      isLoading: true,

      setCurrentView: (view) => set({ currentView: view, selectedListId: null }),
      setSelectedListId: (id) => set({ selectedListId: id, currentView: 'all' }),
      setSearchQuery: (query) => set({ searchQuery: query }),

      addList: (name, color) => {
        const lists = get().lists;
        const newList: List = {
          id: generateId(),
          name,
          color,
          order: lists.length,
          createdAt: new Date().toISOString(),
        };
        set({ lists: [...lists, newList] });
      },

      updateList: (id, updates) => {
        set({
          lists: get().lists.map((l) => (l.id === id ? { ...l, ...updates } : l)),
        });
      },

      deleteList: (id) => {
        set({
          lists: get().lists.filter((l) => l.id !== id),
          reminders: get().reminders.filter((r) => r.listId !== id),
        });
      },

      reorderLists: (lists) => {
        set({ lists: lists.map((l, i) => ({ ...l, order: i })) });
      },

      addReminder: (listId, title) => {
        const newReminder: Reminder = {
          id: generateId(),
          listId,
          title,
          priority: 'none',
          flagged: false,
          completed: false,
          createdAt: new Date().toISOString(),
        };
        set({ reminders: [newReminder, ...get().reminders] });
      },

      updateReminder: (id, updates) => {
        set({
          reminders: get().reminders.map((r) => (r.id === id ? { ...r, ...updates } : r)),
        });
      },

      deleteReminder: (id) => {
        set({ reminders: get().reminders.filter((r) => r.id !== id) });
      },

      toggleComplete: (id) => {
        const reminder = get().reminders.find((r) => r.id === id);
        if (reminder) {
          set({
            reminders: get().reminders.map((r) =>
              r.id === id
                ? {
                    ...r,
                    completed: !r.completed,
                    completedAt: !r.completed ? new Date().toISOString() : undefined,
                  }
                : r
            ),
          });
        }
      },

      toggleFlag: (id) => {
        set({
          reminders: get().reminders.map((r) =>
            r.id === id ? { ...r, flagged: !r.flagged } : r
          ),
        });
      },

      loadFromStorage: async () => {
        const data = await loadData();
        if (data.lists.length > 0 || data.reminders.length > 0) {
          set({ lists: data.lists, reminders: data.reminders, isLoading: false });
        } else {
          set({ isLoading: false });
        }
      },
    }),
    {
      name: 'reminders-storage',
      storage: createJSONStorage(() => localStorage),
      partialize: (state) => ({
        lists: state.lists,
        reminders: state.reminders,
      }),
    }
  )
);
