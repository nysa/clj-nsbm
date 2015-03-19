(defproject com.nysavann/clj-nsbm "0.1.0"
  :description "Clojure(Script) library for building Netscape Bookmark files"
  :url "https://github.com/nysa/clj-nsbm"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3126"]]
  :jar-exclusions [#"\.cljx|\.DS_Store"]
  :source-paths ["src/cljx"]
  :test-paths ["target/test-classes"]
  :profiles {:dev {:plugins [[com.keminglabs/cljx "0.6.0"]
                             [lein-cljsbuild "1.0.5"]
                             [com.cemerick/clojurescript.test "0.3.3"]]
                   :prep-tasks [["cljx" "once"] "javac" "compile"]
                   :auto-clean false
                   :aliases {"cleantest" ["do" "clean," "cljx" "once," "test," "cljsbuild" "test"]
                             "deploy" ["do" "clean," "cljx" "once," "deploy" "clojars"]}}}
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :cljs}
                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :clj}
                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :cljs}]}
  :cljsbuild {:test-commands {"node" ["node" :node-runner "target/testable.js"]}
              :builds [{:source-paths ["target/classes" "target/test-classes"]
                        :compiler {:output-to "target/testable.js"
                                   :optimizations :simple
                                   :pretty-print true}}]})
