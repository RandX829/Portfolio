## Context

A local-only web application mimicking iOS 26 Reminders. No backend - all data stored in browser via localforage. Target users want a beautiful, functional task manager that feels native iOS without cloud sync complexity.

**Tech Stack:**
- React 19 + Vite + TypeScript
- Tailwind CSS + shadcn/ui
- Zustand for state management
- dayjs for date handling
- localforage for JSON persistence

## Goals / Non-Goals

**Goals:**
- Replicate iOS 26 Reminders visual design (pill tabs, circular checkboxes, colored list tiles, due date badges, priority dots)
- Provide Today/Scheduled/All/Flagged smart views
- Full CRUD for lists and reminders
- Local persistence with no server required
- Responsive desktop/tablet layout (1024px+)

**Non-Goals:**
- Mobile-first responsive design
- Cloud sync / iCloud integration
- Collaboration / sharing
- Reminder notifications (browser notifications optional)
- Recurring reminders
- Location-based triggers

## Decisions

### 1. Zustand over Redux/Context
**Decision**: Use Zustand for state management

**Rationale**: Simpler API, less boilerplate than Redux, works better with localforage persistence via Zustand middleware. React 19's fine-grained reactivity pairs well with Zustand's minimal approach.

**Alternative Considered**: React Context + useReducer - too much boilerplate for nested state.

### 2. localforage over IndexedDB directly
**Decision**: Use localforage for persistence

**Rationale**: localforage provides a simple Promise-based API over IndexedDB, handles serialization automatically, and works offline by default. Good fit for JSON storage of lists and reminders.

**Alternative Considered**: raw IndexedDB - too verbose; IndexedDB with wrapper - redundant since localforage exists.

### 3. Tailwind + shadcn/ui for styling
**Decision**: Use Tailwind CSS with shadcn/ui components

**Rationale**: shadcn/ui provides accessible, customizable components that can be styled to match iOS aesthetics. Tailwind allows rapid styling with custom colors and border radii.

**Alternative Considered**: Styled Components - more runtime overhead; CSS Modules - less utility for rapid iteration.

### 4. dayjs over date-fns
**Decision**: Use dayjs for date handling

**Rationale**: Smaller bundle size (2KB vs 70KB), immutable API, i18n support for date formatting that matches iOS locales.

**Alternative Considered**: date-fns - larger bundle; native Date - painful API.

### 5. Single store with normalized data
**Decision**: Use Zustand store with normalized entities (lists, reminders as separate arrays with IDs)

**Rationale**: Normalized state makes it easier to update individual items, filter by list, and manage complex relationships. Prevents data duplication.

## Data Model

```typescript
interface List {
  id: string;
  name: string;
  color: string; // hex color
  icon?: string;
  order: number;
  createdAt: string;
}

interface Reminder {
  id: string;
  listId: string;
  title: string;
  notes?: string;
  dueDate?: string; // ISO date
  dueTime?: string; // HH:mm
  priority: 'none' | 'low' | 'medium' | 'high';
  flagged: boolean;
  completed: boolean;
  completedAt?: string;
  createdAt: string;
}
```

## Architecture

```
┌─────────────────────────────────────────────┐
│                  UI Layer                    │
│  (React Components + shadcn/ui)             │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│             State Layer                      │
│  (Zustand Store)                             │
│  - lists[], reminders[]                      │
│  - currentView (today/scheduled/all/flagged)│
│  - selectedListId                            │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│           Persistence Layer                  │
│  (localforage)                               │
│  - Key: 'reminders-data'                     │
│  - Value: { lists: [], reminders: [] }       │
└─────────────────────────────────────────────┘
```

## Risks / Trade-offs

- **[Risk] No conflict resolution** → Mitigation: Single-user local app, no sync conflicts possible
- **[Risk] Browser storage limits** → Mitigation: Most browsers give ~50MB+ for localStorage/IndexedDB, sufficient for typical reminder use
- **[Risk] Data loss on clear cache** → Mitigation: Consider export feature in v2; warn user that clearing browser data deletes reminders
- **[Risk] No offline indicator** → Mitigation: App is always "offline" - no network dependency

## Migration Plan

This is v1 - no migration needed. Initial release creates fresh local storage.

## Open Questions

1. **Default lists**: Should we seed with "Personal", "Work", "Groceries" on first load?
2. **Search UI**: Floating search bar (iOS style) or integrated in header?
3. **Completed retention**: How long to keep completed reminders? (iOS keeps indefinitely)
4. **Keyboard shortcuts**: Support Cmd+N for new reminder, etc.?
