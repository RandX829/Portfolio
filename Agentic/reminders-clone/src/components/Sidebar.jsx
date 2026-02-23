import React, { useState, useRef } from 'react';
import { useStore } from '../store/useStore';
import IconCircle from './common/IconCircle';
import dayjs from 'dayjs';
import {
    Calendar,
    Clock,
    Archive,
    CheckCircle,
    User,
    Briefcase,
    Search,
    Plus,
    Download,
    Upload
} from 'lucide-react';
import { clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

const cn = (...inputs) => twMerge(clsx(inputs));

const iconMap = {
    calendar: Calendar,
    clock: Clock,
    archive: Archive,
    'check-circle': CheckCircle,
    user: User,
    briefcase: Briefcase,
};

const Sidebar = () => {
    const { lists, reminders, selectedListId, setSelectedListId, searchQuery, setSearchQuery, addList } = useStore();
    const [isAddingList, setIsAddingList] = useState(false);
    const [newListLabel, setNewListLabel] = useState('');

    const smartLists = lists.filter(l => l.isSmart);
    const userLists = lists.filter(l => !l.isSmart);

    const getRemindersCount = (listId) => {
        if (listId === 'all') return reminders.length;
        if (listId === 'completed') return reminders.filter(r => r.isCompleted).length;
        if (listId === 'today') return reminders.filter(r => {
            const today = dayjs().format('YYYY-MM-DD');
            return r.dueDate && dayjs(r.dueDate).isSame(today, 'day');
        }).length;
        if (listId === 'scheduled') return reminders.filter(r => !!r.dueDate).length;
        return reminders.filter(r => r.listId === listId).length;
    };

    const handleAddList = (e) => {
        if (e.key === 'Enter' && newListLabel.trim()) {
            const colors = ['blue', 'red', 'orange', 'yellow', 'green', 'purple', 'pink'];
            const randomColor = colors[Math.floor(Math.random() * colors.length)];
            addList({
                name: newListLabel,
                color: randomColor,
                icon: 'archive'
            });
            setNewListLabel('');
            setIsAddingList(false);
        }
    };

    return (
        <div className="w-80 h-full bg-apple-gray-6 border-r border-apple-gray-4 flex flex-col p-4">
            {/* Search */}
            <div className="relative mb-6">
                <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-apple-gray" />
                <input
                    type="text"
                    placeholder="Search"
                    className="w-full bg-apple-gray-5 rounded-xl py-2 pl-10 pr-4 text-sm focus:outline-none focus:ring-2 focus:ring-apple-blue/50"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                />
            </div>

            {/* Smart Lists Grid */}
            <div className="grid grid-cols-2 gap-3 mb-8">
                {smartLists.map((list) => {
                    const Icon = iconMap[list.icon] || Archive;
                    const isActive = selectedListId === list.id;
                    const count = getRemindersCount(list.id);

                    return (
                        <button
                            key={list.id}
                            onClick={() => setSelectedListId(list.id)}
                            className={cn(
                                "p-3 rounded-xl bg-white shadow-sm flex flex-col items-start transition-all",
                                isActive ? "ring-2 ring-apple-blue shadow-md" : "hover:bg-apple-gray-5"
                            )}
                        >
                            <IconCircle
                                icon={Icon}
                                color={list.color}
                                size={20}
                                className="mb-2"
                            />
                            <div className="flex justify-between w-full items-baseline">
                                <span className="text-xs font-semibold text-apple-gray uppercase tracking-wider">{list.name}</span>
                                <span className="text-xl font-bold">{count}</span>
                            </div>
                        </button>
                    );
                })}
            </div>

            {/* My Lists Section */}
            <div className="flex-1 overflow-y-auto">
                <h2 className="px-2 text-xl font-bold mb-2">My Lists</h2>
                <div className="space-y-1">
                    {userLists.map((list) => {
                        const Icon = iconMap[list.icon] || Archive;
                        const isActive = selectedListId === list.id;
                        const count = getRemindersCount(list.id);

                        return (
                            <button
                                key={list.id}
                                onClick={() => setSelectedListId(list.id)}
                                className={cn(
                                    "w-full flex items-center px-2 py-2 rounded-lg transition-colors group",
                                    isActive ? "bg-apple-blue text-white" : "hover:bg-apple-gray-5"
                                )}
                            >
                                <IconCircle
                                    icon={Icon}
                                    color={isActive ? 'white' : list.color}
                                    size={14}
                                    className={cn(
                                        "mr-3 p-1.5",
                                        isActive && "text-apple-blue"
                                    )}
                                />
                                <span className="text-sm font-medium">{list.name}</span>
                                <span className="ml-auto text-xs opacity-60 font-medium">{count}</span>
                            </button>
                        );
                    })}

                    {isAddingList && (
                        <div className="flex items-center px-2 py-2 rounded-lg bg-apple-gray-5 mt-1">
                            <IconCircle icon={Archive} color="gray" size={14} className="mr-3 p-1.5" />
                            <input
                                autoFocus
                                type="text"
                                className="bg-transparent border-none focus:outline-none text-sm w-full font-medium"
                                value={newListLabel}
                                onChange={(e) => setNewListLabel(e.target.value)}
                                onKeyDown={handleAddList}
                                onBlur={() => {
                                    if (!newListLabel.trim()) setIsAddingList(false);
                                }}
                            />
                        </div>
                    )}
                </div>
            </div>

            {/* Add List Footer */}
            <div className="mt-auto border-t border-apple-gray-4 pt-4 space-y-2">
                <button
                    onClick={() => setIsAddingList(true)}
                    className="flex items-center w-full text-apple-blue font-semibold px-2 hover:bg-apple-gray-5 py-2 rounded-lg transition-all"
                >
                    <Plus size={20} className="mr-2" />
                    <span>Add List</span>
                </button>

                <div className="flex gap-2">
                    <button
                        onClick={() => {
                            const data = useStore.getState();
                            const blob = new Blob([JSON.stringify({
                                lists: data.lists,
                                reminders: data.reminders
                            }, null, 2)], { type: 'application/json' });
                            const url = URL.createObjectURL(blob);
                            const a = document.createElement('a');
                            a.href = url;
                            a.download = `reminders-backup-${dayjs().format('YYYY-MM-DD')}.json`;
                            a.click();
                        }}
                        className="flex-1 flex items-center justify-center gap-2 text-apple-gray font-medium text-xs bg-apple-gray-5 hover:bg-apple-gray-4 py-2 rounded-lg transition-all"
                        title="Export JSON"
                    >
                        <Download size={14} />
                        <span>Export</span>
                    </button>

                    <button
                        onClick={() => document.getElementById('json-import').click()}
                        className="flex-1 flex items-center justify-center gap-2 text-apple-gray font-medium text-xs bg-apple-gray-5 hover:bg-apple-gray-4 py-2 rounded-lg transition-all"
                        title="Import JSON"
                    >
                        <Upload size={14} />
                        <span>Import</span>
                    </button>
                    <input
                        id="json-import"
                        type="file"
                        accept=".json"
                        className="hidden"
                        onChange={(e) => {
                            const file = e.target.files[0];
                            if (file) {
                                const reader = new FileReader();
                                reader.onload = (event) => {
                                    try {
                                        const data = JSON.parse(event.target.result);
                                        if (confirm('Importing data will replace your current lists and reminders. Continue?')) {
                                            useStore.getState().importData(data);
                                        }
                                    } catch (err) {
                                        alert('Invalid JSON file');
                                    }
                                };
                                reader.readAsText(file);
                            }
                        }}
                    />
                </div>
            </div>
        </div>
    );
};

export default Sidebar;
