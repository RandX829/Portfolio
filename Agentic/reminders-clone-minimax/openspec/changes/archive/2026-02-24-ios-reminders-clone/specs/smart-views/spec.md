## ADDED Requirements

### Requirement: View Today reminders
The Today view SHALL show all reminders due today (with or without a specific time).

#### Scenario: Today view shows today's reminders
- **WHEN** user taps the "Today" pill tab
- **THEN** only reminders with dueDate = today are displayed
- **AND** reminders are sorted by time (if set), then by priority (High first)

#### Scenario: Overdue reminders show in Today
- **WHEN** a reminder is overdue (past its due date/time)
- **THEN** it appears in the Today view with an overdue badge (red)

### Requirement: View Scheduled reminders
The Scheduled view SHALL show all reminders with a future due date.

#### Scenario: Scheduled view shows future reminders
- **WHEN** user taps the "Scheduled" pill tab
- **THEN** only reminders with dueDate > today are displayed
- **AND** reminders are grouped by date (Today, Tomorrow, This Week, Later)
- **AND** within each group, sorted by time then priority

### Requirement: View All reminders
The All view SHALL show all reminders (completed and active) across all lists.

#### Scenario: All view shows everything
- **WHEN** user taps the "All" pill tab
- **THEN** all reminders from all lists are displayed
- **AND** grouped by list with list headers
- **AND** completed items shown at the bottom of each list group (collapsed by default)

### Requirement: View Flagged reminders
The Flagged view SHALL show only flagged reminders.

#### Scenario: Flagged view shows flagged items
- **WHEN** user taps the "Flagged" pill tab
- **THEN** only reminders where flagged = true are displayed
- **AND** sorted by due date (overdue first), then by priority

### Requirement: View list-specific reminders
Users SHALL be able to view all reminders in a specific list.

#### Scenario: View list content
- **WHEN** user taps on a list in the sidebar
- **THEN** all reminders in that list are shown
- **AND** grouped by status (active first, then completed)

### Requirement: View completed reminders
Users SHALL be able to see completed reminders in a collapsible section.

#### Scenario: Show completed section
- **WHEN** reminders are completed
- **THEN** they appear in a "Completed" section at the bottom
- **AND** the section is collapsible (collapsed by default)
- **AND** shows count of completed items (e.g., "Completed (5)")
