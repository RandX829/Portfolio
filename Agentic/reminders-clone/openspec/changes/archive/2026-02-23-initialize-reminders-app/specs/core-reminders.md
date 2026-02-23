## ADDED Requirements

### Requirement: List Management
Users can create, edit, and delete lists to organize their reminders.

#### Scenario: Create a new list
- **WHEN** the user provides a name and selects a color/icon
- **THEN** a new list is created and appears in the sidebar

### Requirement: Reminder Management
Users can add, edit, and toggle completion of reminders within a list.

#### Scenario: Mark reminder as complete
- **WHEN** the user clicks the checkbox next to a reminder
- **THEN** the reminder's status is updated to completed and its visual style changes

### Requirement: Smart Lists
Automatic groups for Today, Scheduled, All, and Completed reminders.

#### Scenario: View Today's reminders
- **WHEN** the user selects the "Today" smart list
- **THEN** all reminders with a due date of today are displayed
