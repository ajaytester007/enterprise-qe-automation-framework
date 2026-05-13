# Flaky Test Reduction Playbook

## Common root causes
- Synchronization issues
- Unstable locators
- Shared test data
- Asynchronous/eventual processing
- Environment instability
- Parallel execution collisions

## Controls used in this framework
- Explicit waits and quick waits
- Resilient CSS/data-test locators
- ThreadLocal driver for parallel safety
- Controlled test data builders
- API-first validation for business rules
- SQL reconciliation for backend truth
- CI artifacts for failure evidence

## Interview phrase
"A failed automated test should guide the engineer toward probable root cause. Otherwise, automation becomes noise instead of a release-confidence asset."
