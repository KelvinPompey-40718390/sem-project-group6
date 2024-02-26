# USE CASE: 14 Generate a Report of the top `N` populated cities in a region where `N` is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *a report of the top `N` populated cities in a region where `N` is provided by myself* to *allow easy access to population information*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains current world population data.

### Success End Condition

A report is available for user to provide to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for population information is sent to the user.

## MAIN SUCCESS SCENARIO

1. User selects report type
2. User inputs region
3. User inputs `N`
4. User requests top `N` populated cities in the region.
5. A Report is generated for the selected region.
6. User provides the data to the organization.

## EXTENSIONS

3 . **User enters invalid value for `N`**
1. User asked to enter a value for `N`  

5 . **Data is not available**:
1. User informs organization that no data available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0