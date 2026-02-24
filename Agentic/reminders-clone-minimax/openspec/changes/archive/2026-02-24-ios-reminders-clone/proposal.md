## Why

Build a local-only, web-based Reminders app that mirrors the iOS 26 Reminders experience. Users want a clean, beautiful task manager with colorful lists, smart sorting (Today/Scheduled/All/Flagged), and native-feeling interactions - without cloud sync complexity.

## What Changes

- New React webapp with iOS 26 Reminders visual design
- Sidebar with colored My Lists (Personal, Work, etc.)
- Main content area with pill-style category tabs (Today, Scheduled, All, Flagged)
- Reminder rows with circular checkboxes, due dates, priority dots, and flag icons
- Local JSON persistence via localforage
- Zustand state management

## Capabilities

### New Capabilities

- **list-management**: Create, edit, delete, and color-code reminder lists
- **reminder-crud**: Create, edit, complete, delete reminders with due dates and priorities
- **smart-views**: Filter reminders by Today, Scheduled (future dates), All, and Flagged
- **local-persistence**: Persist all data locally using localforage
- **search**: Search across all reminders

### Modified Capabilities

None - this is a new project.

## Impact

- New frontend-only React app
- No backend required - all data stored in browser via localforage
- Dependencies: React 19, Vite, TypeScript, Tailwind, shadcn/ui, Zustand, dayjs, localforage
