## ADDED Requirements

### Requirement: Persist lists to local storage
All lists SHALL be persisted to browser local storage using localforage.

#### Scenario: Lists persist across page reloads
- **WHEN** user creates, edits, or deletes a list
- **THEN** the change is saved to localforage
- **AND** when the page is reloaded, the lists are restored

### Requirement: Persist reminders to local storage
All reminders SHALL be persisted to browser local storage using localforage.

#### Scenario: Reminders persist across page reloads
- **WHEN** user creates, edits, completes, uncompletes, or deletes a reminder
- **THEN** the change is saved to localforage
- **AND** when the page is reloaded, all reminders are restored with their state

### Requirement: Persist list order
The order of lists in the sidebar SHALL persist across sessions.

#### Scenario: List order persists
- **WHEN** user drags lists to reorder them
- **THEN** the new order is saved
- **AND** restored on page reload

### Requirement: Auto-save all changes
All changes SHALL be saved automatically with no explicit save action required.

#### Scenario: Auto-save on change
- **WHEN** any change occurs (create, edit, delete, complete, flag, etc.)
- **THEN** the data is saved to localforage immediately
- **AND** no "Save" button or confirmation is needed
