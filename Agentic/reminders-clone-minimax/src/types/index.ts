export interface List {
  id: string;
  name: string;
  color: string;
  icon?: string;
  order: number;
  createdAt: string;
}

export interface Reminder {
  id: string;
  listId: string;
  title: string;
  notes?: string;
  dueDate?: string;
  dueTime?: string;
  priority: 'none' | 'low' | 'medium' | 'high';
  flagged: boolean;
  completed: boolean;
  completedAt?: string;
  createdAt: string;
}

export type ViewType = 'today' | 'scheduled' | 'all' | 'flagged';

export interface AppState {
  lists: List[];
  reminders: Reminder[];
  currentView: ViewType;
  selectedListId: string | null;
  searchQuery: string;
}
