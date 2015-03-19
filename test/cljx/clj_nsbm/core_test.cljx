(ns clj-nsbm.core-test
  #+cljs (:require-macros [cemerick.cljs.test :refer [deftest is testing]])
  (:require [clj-nsbm.core :as nsbm]
            #+clj [clojure.test :refer [deftest is testing]]
            #+cljs [cemerick.cljs.test :as t]))

(deftest build-item-test
  (testing "maps containing key :url"
    (is (= (nsbm/build-item {:title "Clojure" :url "http://clojure.org"}) "<DT><A HREF=\"http://clojure.org\">Clojure</A>")))
  (testing "maps not containing key :url"
    (is (= (nsbm/build-item {:title "Blogs" :children []}) "<DT><H3 FOLDED>Blogs</H3><DL><p></DL><p>"))))

(deftest build-subfolder-test
  (is (= (nsbm/build-subfolder {}) "<DT><H3 FOLDED></H3><DL><p></DL><p>"))
  (is (= (nsbm/build-subfolder {:title "Empty"}) "<DT><H3 FOLDED>Empty</H3><DL><p></DL><p>"))
  (is (= (nsbm/build-subfolder {:title "Empty" :children []}) "<DT><H3 FOLDED>Empty</H3><DL><p></DL><p>"))
  (is (= (nsbm/build-subfolder {:title "Blogs" :children [{:url "https://github.com/blog" :title "The GitHub Blog"}]}) "<DT><H3 FOLDED>Blogs</H3><DL><p><DT><A HREF=\"https://github.com/blog\">The GitHub Blog</A></DL><p>")))

(deftest build-shortcut-test
  (is (= (nsbm/build-shortcut {:url "http://www.nasa.gov"}) "<DT><A HREF=\"http://www.nasa.gov\"></A>"))
  (is (= (nsbm/build-shortcut {:title "Haskell" :url "https://www.haskell.org"}) "<DT><A HREF=\"https://www.haskell.org\">Haskell</A>"))
  (is (= (nsbm/build-shortcut {:title "GitHub" :url "https://github.com" :date 0}) "<DT><A HREF=\"https://github.com\" ADD_DATE=\"0\">GitHub</A>")))
