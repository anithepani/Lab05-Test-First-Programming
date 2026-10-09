# Lab 05 - Test First Programming (Tweet Tweet)

SE-314 Software Construction - Lab 05
Name: Aryan Zia
CMS: 505899

Solution to Problems 1 and 2 of Problem Set 1 ("Tweet Tweet") from MIT 6.005, written test-first with JUnit.

## Scope

Only Problems 1 and 2 are covered, as required by the lab handout:

- **Extract** (`src/twitter/Extract.java`)
  - `getTimespan(tweets)` - minimum-length time interval containing every tweet's timestamp.
  - `getMentionedUsers(tweets)` - set of Twitter usernames mentioned in the tweet text, case-insensitive and reported once each.
- **Filter** (`src/twitter/Filter.java`)
  - `writtenBy(tweets, username)` - tweets whose author is `username`.
  - `inTimespan(tweets, timespan)` - tweets sent during the timespan (endpoints included).
  - `containing(tweets, words)` - tweets that contain at least one of the given words (case-insensitive).

The `SocialNetwork` classes (Problems 3 and 4) are not part of this lab and have been removed.

## Test-first approach

For each method the tests were written before the implementation:

1. A testing strategy comment at the top of each test class partitions the input and output space.
2. Every test names the partitions it covers with a `// covers:` comment.
3. The suites were run against the unimplemented methods first (red), then the implementations were added until all tests passed (green).

- `test/twitter/ExtractTest.java` - 12 tests
- `test/twitter/FilterTest.java` - 14 tests

The empty-list case of `getTimespan` is deliberately not tested, because the specification does not define its return value.

## Layout

```
ps1/
  src/twitter/     Extract, Filter, Main, Timespan, Tweet, TweetReader
  test/twitter/    ExtractTest, FilterTest
  lib/             junit-4.13.2.jar, hamcrest-core-1.3.jar, javax.json-1.0.jar
  tweets.txt       sample tweets used by Main
```

## Running

### VS Code

Open the `ps1` folder with the Extension Pack for Java. Tests appear in the Testing view and can be run from there. `Main` is available as a run configuration.

### Terminal (JDK 17)

Compile:

```
javac -d bin -cp "lib/*" src/twitter/*.java test/twitter/*.java
```

Run the tests:

```
java -ea -cp "bin;lib/*" org.junit.runner.JUnitCore twitter.ExtractTest twitter.FilterTest
```

Run the sample program:

```
java -cp "bin;lib/*" twitter.Main
```

`Main` and `tweets.txt` are not used for grading and can be changed freely.
