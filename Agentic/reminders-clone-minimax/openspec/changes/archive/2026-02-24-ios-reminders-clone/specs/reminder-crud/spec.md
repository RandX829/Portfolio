## ADDED Requirements

### Requirement: Create a reminder
Users SHALL be able to create a new reminder with a title, optionally in a specific list.

#### Scenario: Create reminder via quick add
- **WHEN** user types in the quick-add field and presses Enter
- **THEN** a new reminder is created in the current list (or default list)
- **AND** the reminder appears at the top of the list

#### Scenario: Create reminder with details
- **WHEN** user taps a reminder to open the detail view
- **AND** enters a title, optional notes, due date, time, priority, and flag status
- **THEN** the reminder is saved with all specified details

### Requirement: Complete a reminder
Users SHALL be able to mark a reminder as completed by tapping the checkbox.

#### Scenario: Complete reminder
- **WHEN** user taps the circular checkbox next to a reminder
- **THEN** the checkbox fills with the list's color
- **AND** the reminder text gets a strikethrough style
- **AND** the reminder moves to the "Completed" section at the bottom
- **AND** a "completedAt" timestamp is recorded

#### Scenario: Uncomplete reminder
- **WHEN** user taps the filled checkbox on a completed reminder
- **THEN** the checkbox becomes hollow again
- **AND** the strikethrough is removed
- **AND** the reminder returns to its original position among active reminders

### Requirement: Edit reminder
Users SHALL be able to edit all reminder fields (title, notes, due date, time, priority, flag).

#### Scenario: Edit reminder details
- **WHEN** user taps on a reminder to open the detail panel
- **AND** modifies any field
- **THEN** changes are saved automatically

### Requirement: Delete a reminder
Users SHALL be able to delete a reminder.

#### Scenario: Delete reminder via swipe
- **WHEN** user swipes left on a reminder and taps "Delete"
- **THEN** the reminder is permanently removed

### Requirement: Set due date and time
Users SHALL be able to set an optional due date and time for a reminder.

#### Scenario: Set due date
- **WHEN** user taps "Due Date" field and selects a date
- **THEN** the reminder displays the due date badge
- **AND** the reminder appears in the Scheduled view

#### Scenario: Set due time
- **WHEN** user enables time and selects a specific time
- **THEN** the reminder displays the time alongside the date
- **AND** the reminder appears in the Today view at the correct time

### Requirement: Set priority
Users SHALL be able to set priority as None, Low, Medium, or High.

#### Scenario: Set priority
- **WHEN** user selects a priority level from the priority picker
- **THEN** the corresponding priority indicator is shown on the reminder row

### Requirement: Flag a reminder
Users SHALL be able to flag/unflag a reminder.

#### Scenario: Flag reminder
- **WHEN** user taps the flag icon on a reminder
- **THEN** the flag turns orange (filled)
- **AND** the reminder appears in the Flagged view

#### Scenario: Unflag reminder
- **WHEN** user taps the filled flag icon
- **THEN** the flag becomes hollow (unfilled)
- **AND** the reminder is removed from the Flagged view
