# clj-nsbm

Clojure library for building Netscape Bookmark files

Based on the file structure defined here: <https://msdn.microsoft.com/en-us/library/aa753582%28v=vs.85%29.aspx>

## Usage

### Document

Build an entire document with a vector of subfolders or shortcuts:

```clojure
user=> (require '[clj-nsbm.core :as nsbm])
nil
user=> (nsbm/build [{:title "Languages"
                     :children [{:title "Clojure" :url "http://clojure.org/"}]}
                    {:title "GitHub" :url "https://github.com/"}])
"<!DOCTYPE NETSCAPE-Bookmark-file-1><!--This is an automatically generated file. It will be read and overwritten. Do Not Edit! --><Title>Bookmarks</Title><H1>Bookmarks</H1><DL><DT><H3 FOLDED>Languages</H3><DL><p><DT><A HREF=\"http://clojure.org/\">Clojure</A></DL><p><DT><A HREF=\"https://github.com/\">GitHub</A></DL>"
```

### Shortcut

A shortcut is a Clojure map containing the `:url` key at a minimum. A shortcut may also contain the keys `:title`, `:date`, `:modified`, and `:visited`. Building a shortcut:

```clojure
user=> (require '[clj-nsbm.core :as nsbm])
nil
user=> (nsbm/build-shortcut {:title "Hacker News" :url "https://news.ycombinator.com/"})
"<DT><A HREF=\"https://news.ycombinator.com/\">Hacker News</A>"
```

### Subfolder

A subfolder is a Clojure map which may contain the keys `:title` and/or `:children`. Building a subfolder:

```clojure
user=> (require '[clj-nsbm.core :as nsbm])
nil
user=> (nsbm/build-subfolder {:title "Folder"
                              :children [{:title "NASA" :url "http://www.nasa.gov/"}]})
"<DT><H3 FOLDED>Folder</H3><DL><p><DT><A HREF=\"http://www.nasa.gov/\">NASA</A></DL><p>"
```

## License

Copyright &copy; 2015 Nysa Vann <<nysa@nysavann.com>>

Distributed under the Eclipse Public License. See LICENSE for details.
