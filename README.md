# clj-nsbm

Clojure(Script) library for building Netscape Bookmark files

Based on: <https://msdn.microsoft.com/en-us/library/aa753582%28v=vs.85%29.aspx>

## Installation

Add the following dependency to your `project.clj` file:

```clojure
(defproject example "0.1.0"
  :dependencies [[com.nysavann/clj-nsbm "0.1.0"]])
```

## Usage

Require the library:

```clojure
(ns example
  (:require [clj-nsbm.core :as nsbm]))
```

### Building a document

Build a document with a vector containing subfolders or shortcuts:

```clojure
(nsbm/build [{:title "Languages"
              :children [{:title "Clojure" :url "http://clojure.org/"}]}
                         {:title "Go" :url "https://golang.org/"}]}])
;;=> "<!DOCTYPE NETSCAPE-Bookmark-file-1><!--This is an automatically generated file. It will be read and overwritten. Do Not Edit! --><Title>Bookmarks</Title><H1>Bookmarks</H1><DL><DT><H3 FOLDED>Languages</H3><DL><p><DT><A HREF=\"http://clojure.org/\">Clojure</A></DL><p><DT><A HREF=\"https://golang.org/\">Go</A></DL>"
```

### Building a shortcut

A shortcut is a Clojure map containing the required key `:url` and optional keys `:title`, `:date`, `:modified`, and `:visited`.

```clojure
(nsbm/build-shortcut {:title "Hacker News" :url "https://news.ycombinator.com/"})
;;=> "<DT><A HREF=\"https://news.ycombinator.com/\">Hacker News</A>"
```

### Building a subfolder

A subfolder is a Clojure map containing the optional keys `:title` and `:children`.

```clojure
(nsbm/build-subfolder {:title "$" :children [{:url "http://dogecoin.com/"}]})
;;=> "<DT><H3 FOLDED>$</H3><DL><p><DT><A HREF=\"http://dogecoin.com/\"></A></DL><p>"
```

## License

Copyright &copy; 2015 Nysa Vann <<nysa@nysavann.com>>

Distributed under the Eclipse Public License. See [LICENSE](LICENSE) for details.
