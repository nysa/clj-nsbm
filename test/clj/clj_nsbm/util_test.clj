(ns clj-nsbm.util-test
  (:require [clojure.test :refer :all]
            [clj-nsbm.util :refer :all]))

(deftest item->attrs-test
  (is (= (item->attrs {:url "http://clojure.org/"} {:url :HREF}) {:HREF "http://clojure.org/"}))
  (is (= (item->attrs {:url "http://clojure.org/"} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org/"}))
  (is (= (item->attrs {:url "http://clojure.org/" :date 0} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org/" :ADD_DATE 0})))
