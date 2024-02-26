# USE CASE: 20 Generate a report to show the top `N` populated capital cities in the world where `N` is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context
As a user I want to view the top `N` populated capital cities in the world where `N` is provided by myself.

### Scope

Organization.

### Level

Primary task.

### Preconditions

We have a list of the capital cities and their respective populations

### Success End Condition

The user receives a report based on their query

### Failed End Condition

No Report is generated.

### Primary Actor

A user within the organisation

### Trigger

A user is required to produce a report

## MAIN SUCCESS SCENARIO

1. User selects report type.
2. User selects a criteria (World).
3. User enters a value for `N`.
4. A Report is produced for the selected criteria; sorted in descending order.
5. User provides the data to the organisation.

## EXTENSIONS
3. **Employee Enters invalid value for `N`**:
   1. User asked to enter a value for `N`

4. **No data error**:
    1. User informs the organization that no data is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0