## 1. Project Setup

- [x] 1.1 Initialize Vite + React + TypeScript project
- [x] 1.2 Install dependencies: Tailwind CSS, shadcn/ui, Zustand, dayjs, localforage
- [x] 1.3 Configure Tailwind with iOS-style colors and border radius
- [x] 1.4 Set up shadcn/ui with default components (button, checkbox, input, dialog, dropdown-menu, popover, etc.)

## 2. Data Layer

- [x] 2.1 Define TypeScript interfaces for List and Reminder
- [x] 2.2 Create localforage persistence layer with save/load functions
- [x] 2.3 Create Zustand store with:
  - lists array
  - reminders array
  - currentView state (today/scheduled/all/flagged)
  - selectedListId state
  - CRUD actions for lists and reminders
  - Persistence middleware

## 3. UI Components - Layout

- [x] 3.1 Create main layout with sidebar (My Lists) + main content area
- [x] 3.2 Build sidebar with list items, colors, and add list button
- [x] 3.3 Create header with view tabs (Today, Scheduled, All, Flagged)
- [x] 3.4 Add search bar in header

## 4. UI Components - Lists

- [x] 4.1 Create NewListDialog for adding lists with name and color picker
- [x] 4.2 Add list edit functionality (rename, change color)
- [x] 4.3 Add list delete with confirmation
- [ ] 4.4 Implement list drag-to-reorder in sidebar

## 5. UI Components - Reminders

- [x] 5.1 Create ReminderRow component with:
  - Circular checkbox (unfilled → filled on complete)
  - Title text (strikethrough when completed)
  - Due date badge (color-coded: red=overdue, yellow=today, gray=future)
  - Priority dots (none/low/medium/high)
  - Flag icon (orange when flagged)
- [x] 5.2 Create ReminderDetailDialog for editing reminder details:
  - Title input
  - Notes textarea
  - Date picker for due date
  - Time picker for due time
  - Priority selector (None/Low/Medium/High)
  - Flag toggle
- [x] 5.3 Implement quick-add input at top of reminder list

## 6. Smart Views

- [x] 6.1 Implement Today view filter (dueDate = today, include overdue)
- [x] 6.2 Implement Scheduled view filter (dueDate > today)
- [x] 6.3 Implement All view (all reminders, grouped by list)
- [x] 6.4 Implement Flagged view filter (flagged = true)
- [x] 6.5 Implement Completed section (collapsed by default)

## 7. Search

- [x] 7.1 Implement search input in header
- [x] 7.2 Filter reminders by title and notes (case-insensitive)
- [x] 7.3 Show list context in search results
- [x] 7.4 Clear search on Escape key

## 8. Polish & iOS Aesthetics

- [x] 8.1 Apply iOS 26 styling: pill-shaped tabs, rounded corners (12-16px), soft shadows
- [x] 8.2 Add proper colors for iOS-style list colors (10+ colors)
- [ ] 8.3 Add swipe-to-delete on reminder rows
- [x] 8.4 Add hover/focus states for accessibility
- [x] 8.5 Ensure proper spacing and typography

## 9. Testing & Validation

- [x] 9.1 Test all CRUD operations for lists
- [x] 9.2 Test all CRUD operations for reminders
- [x] 9.3 Test persistence (refresh page, verify data remains)
- [x] 9.4 Test all view filters work correctly
- [x] 9.5 Test search functionality

## 10. Final Steps

- [x] 10.1 Verify build succeeds without errors
- [x] 10.2 Test in browser for any runtime errors
- [x] 10.3 Seed with sample data if needed for demo
