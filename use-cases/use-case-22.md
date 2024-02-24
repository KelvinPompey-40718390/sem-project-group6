# USE CASE: 22 The top  `N`  populated capital cities in a region where  `N`  is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a User I want to view the Top 'N' populated **capital cities** in a **region** where I provide N to have easy access to this information.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database already exists with the population information.

### Success End Condition

The User can select a **region**, enter a value for `N` and see the Top N populated **capital cities** for the selected **region**. 

### Failed End Condition

No Report is produced.

### Primary Actor

User

### Trigger

A request for the top `N` populated capital cities in a region is sent to the employee.

## MAIN SUCCESS SCENARIO

1. User selects report type.
2. User selects a region.
3. User enters a value for `N`.
4. A Report is produced for the selected region; sorted Largest to Smallest population.
5. User provides the data to the organisation.

## EXTENSIONS

3. **User Enters invalid value for `N`**:
   1. User asked to enter a value for `N`
4. **No data error**:
   1. User informs the organization that no data is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0