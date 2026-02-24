## Why

The goal is to provide a premium, local-first web application that mimics the experience of the iOS Reminders app. This change initializes the project with a robust design, core data models, and a persistence strategy that ensures reliability without a complex backend.

## What Changes

This change sets up the foundation of the application including:
- Core data models for Lists and Reminders.
- Local-first persistence using `localStorage`.
- A responsive, Apple-inspired UI layout.
- Integration of `dayjs` for date management and `lucide-react` for iconography.

## Capabilities

### New Capabilities
- `core-reminders`: Management of custom lists and reminders with titles, notes, due dates, and priorities.
- `data-persistence`: Seamless saving/loading via `localStorage` and support for JSON file import/export.

### Modified Capabilities
- (None)

## Impact

- **New System**: This is a greenfield initialization.
- **Dependencies**: React, Vite, Tailwind CSS, Lucide-React, Dayjs, Zustand.
