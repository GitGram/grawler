# grawler [![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/GitGram/grawler/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

GitHub Crawler - uses GitHub API to crawl and organize data

## Usage

A `config.yml` is supplied as an input to grawler.

### Example YAML File

```yml
all_users: false #If = true, all entities are saved, if = false, entities are not saved and if = a number, only that amount of records are saved into the database
  user # gitgram looks for a 'user' key in the parent's json, if found that url is fetched and data is retrieved.
  collaborator: 10 # A number 'n' implies only first n entries will be stored in the database.
    some_other_attrib: false # true implies all entries will be stored and false implies none. A number means only those number of entries will be stored starting from the first.
all_repos: true
```

## Contributing

### Requirements

* Eclipse
* Oracle Java JDK 1.7
* MoreUnit plugin for tests

## LICENSE

[MIT License](http://en.wikipedia.org/wiki/MIT_License)
