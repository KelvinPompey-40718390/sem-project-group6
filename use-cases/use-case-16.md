# # USE CASE: 16 Generate a report to show the top `N` populated cities in a district where `N` is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a user I want to view the top `N` populated cities in a district where `N` is provided by myself.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We have data containing the cities and their populations

### Success End Condition

The user receives a report based on their query

### Failed End Condition

No report is generated

### Primary Actor

User

### Trigger

A user is required to produce a report

## MAIN SUCCESS SCENARIO

1. User selects report type.
2. User selects a district.
3. User enters a value for `N`.
4. A Report is produced for the selected region.
5. User provides the data to the organisation.


## EXTENSIONS

3. **User enters invalid value for `N`**
   1. User asked to enter a value for `N`

4. **Data is not available**:  
   1. User informs organization that no data available.


## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0