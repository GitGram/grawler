# grawler

GitHub Crawler - uses GitHub API to crawl and organize data

## Usage

A `config.json` is supplied as an input to grawler.

### Example JSON File

```json
{
    "allUsers": {
        "save": "true",
        "user": {
            "save": "true"
        },
        "collaborators": {
            "save": "10"
        },
        "someOtherAttribute": {
            "save": "true"
        }
    },
    "allRepos": {
        "save": "true"
    }
}
```

* If "save" = true for a key, then all entities for that key is saved, if = false, entities are not saved, if = a number n, then n number of records are saved.
* gitgram looks for a 'user' key in the parent's json, if found that url is fetched and data is retrieved.
* Any number of attributes may be specified.

## Contributing

### Requirements

* Eclipse
* Oracle Java JDK 1.7
* MoreUnit plugin for tests

## LICENSE

[MIT License](http://en.wikipedia.org/wiki/MIT_License)


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/GitGram/grawler/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

