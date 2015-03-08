(ns clj-nsbm.util-test
  (:require [clojure.test :refer :all]
            [clj-nsbm.util :refer :all]))

(deftest attr-str-test
  (is (= (attr-str ["href" "http://clojure.org"]) " href=\"http://clojure.org\""))
  (is (= (attr-str [:href  "http://clojure.org"]) " href=\"http://clojure.org\"")))

(deftest attrs-str-test
  (is (= (attrs-str {:title "Clojure"}) " title=\"Clojure\""))
  (is (= (attrs-str {:title "Clojure" :href "http://clojure.org"}) " title=\"Clojure\" href=\"http://clojure.org\"")))

(deftest item-attrs-test
  (is (= (item-attrs {:url "http://clojure.org"} {:url :HREF}) {:HREF "http://clojure.org"}))
  (is (= (item-attrs {:url "http://clojure.org"} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org"}))
  (is (= (item-attrs {:url "http://clojure.org" :date 0} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org" :ADD_DATE 0})))
