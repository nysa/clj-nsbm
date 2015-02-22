(ns clj-nsbm.core-test
  (:require [clojure.test :refer :all]
            [clj-nsbm.core :refer :all]))

(deftest attrs->str-test
  (is (= (attrs->str {:foo "bar"}) " foo=\"bar\"")))
  (is (= (attrs->str {:foo "bar" :bar "baz"}) " bar=\"baz\" foo=\"bar\""))

(deftest item->attrs-test
  (is (= (item->attrs {:url "http://clojure.org/"} {:url :HREF}) {:HREF "http://clojure.org/"}))
  (is (= (item->attrs {:url "http://clojure.org/"} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org/"}))
  (is (= (item->attrs {:url "http://clojure.org/" :date 0} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org/" :ADD_DATE 0})))
