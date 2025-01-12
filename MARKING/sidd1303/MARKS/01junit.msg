----------------------------------- Section 1: JUnit Testing Report -----------------------------------
--------------------------------------------------------------------------------------------------------------
| Metric                         | Count                | Criteria Met                   |
--------------------------------------------------------------------------------------------------------------
| .getMove() calls               | 1                    | Yes: +0.5                      |
| .play() calls                  | 2                    | Yes: +0.5                      |
--------------------------------------------------------------------------------------------------------------
| @org.junit.jupiter.api.Test    | 8                    | Yes                            |
| @AfterAll                      | 0                    | No                             |
| @BeforeEach                    | 5                    | Yes                            |
| @AfterEach                     | 1                    | No                             |
| @Test                          | 11                   | Yes                            |
| @BeforeAll                     | 0                    | No                             |
--------------------------------------------------------------------------------------------------------------
Total Annotations Counted for sidd1303: 25
--------------------------------------------------------------------------------------------------------------

----------------------------------- Section 2: Results Table ------------------------------------
+------------------------------------------+-------------------------+-----------------------------------------------+
| Criteria                                 | Score                   | Comments                                      |
+------------------------------------------+-------------------------+-----------------------------------------------+
| Good code coverage                       |    1.00 / 1              | Based on assumed partial code coverage.       |
+------------------------------------------+-------------------------+-----------------------------------------------+
| All public methods covered               |    1.00 / 1              | Based on .getMove() and .play() calls.        |
+------------------------------------------+-------------------------+-----------------------------------------------+
| Effective use of JUnit annotations       |    1.00 / 1              | Counts for each annotation type checked.      |
+------------------------------------------+-------------------------+-----------------------------------------------+
| **Total Score**                          |    3.00 / 3              | Check 01junit.out for more details.           |
+------------------------------------------+-------------------------+-----------------------------------------------+


---------------------------------- Section 3: JUnit Testing Marking Criteria ----------------------------------

Criteria for grading JUnit tests:

1. **Good code coverage** - Awarded based on reasonable coverage of core functionalities. Full code coverage is not required.

2. **All public methods covered** - Assessed by verifying tests for essential public methods:

   - **.getMove()**: At least one call in tests → +0.5
   - **.play()**: At least one call in tests → +0.5

   **Why these methods?**
   The original code already included test calls for most public methods. We specifically looked for additional test cases covering '.getMove()' and '.play()' to ensure you wrote supplementary tests for these methods.

3. **Effective use of JUnit annotations** - Evaluated based on the use of essential JUnit annotations such as '@BeforeEach', '@AfterEach', '@Test', etc. An additional use of any one annotation, beyond the provided thresholds below, qualifies for +1.

   Annotations Thresholds:
   - **@Test**: 7 instances
   - **@org.junit.jupiter.api.Test**: 7 instances
   - **@BeforeEach**: 3 instances
   - **@AfterEach**: 1 instances
   - **@BeforeAll**: 0 instances
   - **@AfterAll**: 0 instances

   If your tests included at least one additional instance in any of these annotation categories, you were awarded +1 for effectively using JUnit.

--------------------------------------------------------------------------------------------------------------


