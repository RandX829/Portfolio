## Context

Initializing a React + Vite + Tailwind CSS application that clones the iOS Reminders experience. The app is local-only, using browser storage for persistence.

## Goals / Non-Goals

**Goals:**
- Implement a responsive, Apple-inspired UI.
- Handle state management for multiple lists and reminders.
- Provide robust local persistence (localStorage + JSON import/export).
- Use `dayjs` for smart list logic (Today, Scheduled).

**Non-Goals:**
- User authentication or cloud sync.
- Collaborative shared lists.
- Complex reminder recurrences (repeat every N days).

## Decisions

- **State Management**: Zustand will be used to manage lists and reminders in a single store. This provides a balance between simplicity and performance for local-only apps.
- **Persistence**: A custom hook or middleware will sync the Zustand store with `localStorage`. The "JSON persistence" requirement is satisfied via manual import/export functionality.
- **Iconography**: Lucide-React icons wrapped in colored circles to mimic SF Symbols.
- **Theming**: Tailwind CSS with custom HSL variables to match Apple's color palette and support dark/light modes.

## Risks / Trade-offs

- **Storage Limit**: `localStorage` has a limit (typically 5MB). Large numbers of reminders with long notes could eventually hit this, though unlikely for a personal clone.
- **Browser Specifics**: JSON Import/Export relies on standard browser APIs which are consistent across modern browsers but require manual user action.
