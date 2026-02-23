import { create } from 'zustand';
import { persist } from 'zustand/middleware';

export const useStore = create(
    persist(
        (set, get) => ({
            lists: [
                { id: 'today', name: 'Today', color: 'blue', icon: 'calendar', isSmart: true },
                { id: 'scheduled', name: 'Scheduled', color: 'orange', icon: 'clock', isSmart: true },
                { id: 'all', name: 'All', color: 'gray', icon: 'archive', isSmart: true },
                { id: 'completed', name: 'Completed', color: 'gray-2', icon: 'check-circle', isSmart: true },
                { id: 'personal', name: 'Personal', color: 'blue', icon: 'user', isSmart: false },
                { id: 'work', name: 'Work', color: 'yellow', icon: 'briefcase', isSmart: false },
            ],
            reminders: [],
            selectedListId: 'all',
            searchQuery: '',

            setSearchQuery: (query) => set({ searchQuery: query }),
            setSelectedListId: (id) => set({ selectedListId: id }),

            addList: (list) => set((state) => ({
                lists: [...state.lists, { ...list, id: crypto.randomUUID(), isSmart: false }]
            })),

            deleteList: (id) => set((state) => ({
                lists: state.lists.filter(l => l.id !== id),
                reminders: state.reminders.filter(r => r.listId !== id),
                selectedListId: state.selectedListId === id ? 'all' : state.selectedListId
            })),

            addReminder: (reminder) => set((state) => ({
                reminders: [
                    ...state.reminders,
                    {
                        ...reminder,
                        id: crypto.randomUUID(),
                        isCompleted: false,
                        createdAt: new Date().toISOString()
                    }
                ]
            })),

            updateReminder: (id, updates) => set((state) => ({
                reminders: state.reminders.map(r => r.id === id ? { ...r, ...updates } : r)
            })),

            deleteReminder: (id) => set((state) => ({
                reminders: state.reminders.filter(r => r.id !== id)
            })),

            toggleReminder: (id) => set((state) => ({
                reminders: state.reminders.map(r =>
                    r.id === id ? { ...r, isCompleted: !r.isCompleted } : r
                )
            })),

            importData: (data) => set({
                lists: data.lists || [],
                reminders: data.reminders || [],
                settings: data.settings || {}
            })
        }),
        {
            name: 'reminders_app_data',
        }
    )
);
