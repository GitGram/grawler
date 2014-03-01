# grawler

GitHub Crawler - uses GitHub API to crawl and organize data

## Usage

A `config.json` is supplied as an input to grawler.

### Example JSON File

```json
{
    "allUsers": {
        "save": "true", # If = true, all entities are saved, else if = false, entities are not saved, else if = a number, only that amount of records are saved into the database
        "user": {
            "save": "true" # gitgram looks for a 'user' key in the parent's json, if found that url is fetched and data is retrieved.
        },
        "collaborators": {
            "save": "10" # A number 'n' implies only first n entries will be stored in the database.
        },
        "someOtherAttribute": { # Any number of attributes may be specified
            "save": "true"
        }
    },
    "allRepos": {
        "save": "true"
    }
}
```

## Contributing

### Requirements

* Eclipse
* Oracle Java JDK 1.7
* MoreUnit plugin for tests

## LICENSE

[MIT License](http://en.wikipedia.org/wiki/MIT_License)


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/GitGram/grawler/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

