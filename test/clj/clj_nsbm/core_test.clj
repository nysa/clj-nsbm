(ns clj-nsbm.core-test
  (:require [clojure.test :refer :all]
            [clj-nsbm.core :refer :all]))

(deftest subfolder-test
  (is (= (subfolder {:title "Blogs" :children [{:url "https://github.com/blog" :title "The GitHub Blog"}]}) "<DT><H3 FOLDED>Blogs</H3><DL><p><DT><A HREF=\"https://github.com/blog\">The GitHub Blog</A></DL><p>")))

(deftest shortcut-test
  (is (= (shortcut {:title "Clojure" :url "http://clojure.org/"}) "<DT><A HREF=\"http://clojure.org/\">Clojure</A>"))
  (is (= (shortcut {:title "GitHub" :url "https://github.com/" :date 0}) "<DT><A HREF=\"https://github.com/\" ADD_DATE=\"0\">GitHub</A>")))
