@payments @regression
Feature: Lending payments validation
  As a quality engineer
  I want executable BDD scenarios for critical payment behavior
  So that business, QA, and engineering share the same definition of done

  @api @db
  Scenario: Posted payment is reflected in the payment ledger
    Given a controlled payment exists for loan "LN100"
    When the payment is processed through the service layer
    Then the payment should be posted in the backend ledger
    And reconciliation should show no amount mismatch
