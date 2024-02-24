# USE CASE: 11 Generate a Report of all the cities in a district organised by largest to smallest population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a **user** I want *a report of all the cities in a district organised by largest population to smallest* to *allow easy access to population information*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains current world population data.

### Success End Condition

A report is available for the user to provide to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for population information is sent to the user.

## MAIN SUCCESS SCENARIO

1. User selects the report type.
2. User selects a district
3. A report is generated based on the report request.
4. User provides the data to the organisation.

## EXTENSIONS
3. **Data is not available**:
    1. User informs organisation that no data available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
