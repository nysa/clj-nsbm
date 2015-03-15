(ns clj-nsbm.core-test
  (:require [clojure.test :refer :all]
            [clj-nsbm.core :refer :all]))

(deftest build-item-test
  (testing "maps containing key :url"
    (is (= (build-item {:title "Clojure" :url "http://clojure.org"}) "<DT><A HREF=\"http://clojure.org\">Clojure</A>")))
  (testing "maps not containing key :url"
    (is (= (build-item {:title "Blogs" :children []}) "<DT><H3 FOLDED>Blogs</H3><DL><p></DL><p>"))))

(deftest build-subfolder-test
  (is (= (build-subfolder {:title "Blogs" :children [{:url "https://github.com/blog" :title "The GitHub Blog"}]}) "<DT><H3 FOLDED>Blogs</H3><DL><p><DT><A HREF=\"https://github.com/blog\">The GitHub Blog</A></DL><p>")))

(deftest build-shortcut-test
  (is (= (build-shortcut {:title "Haskell" :url "https://www.haskell.org"}) "<DT><A HREF=\"https://www.haskell.org\">Haskell</A>"))
  (is (= (build-shortcut {:title "GitHub" :url "https://github.com" :date 0}) "<DT><A HREF=\"https://github.com\" ADD_DATE=\"0\">GitHub</A>")))
