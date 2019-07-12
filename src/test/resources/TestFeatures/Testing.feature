@testing
Feature: API

  @apiUi
  Scenario: Only api and ui job
    Given user is authorized to create post
    When user creates post with "Cool title", "Cool content" and "publish"
    Then user verifies the post with ui

  @uiApi
  Scenario: Only ui and api job
    Given user is authorized to create post through ui
    When  user creates post with "Cool title", "Cool content" and "publish"
    Then user verifies the post with api

  @uiDB
  Scenario: Only ui and DB job
    Given user is authorized to create post through ui
    When  user creates post with "Cool title", "Cool content" and "publish"
    Then user verifies the post with DB

  @DBapi
  Scenario: Only DB and api job
    Given user is authorized to create post
    When user creates post with "insert query"
    Then user verifies the post with api