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

(deftest escape-html-test
  (is (= (escape-html "zero < one") "zero &lt; one"))
  (is (= (escape-html "two > one") "two &gt; one"))
  (is (= (escape-html "'single' & \"double\"") "&#39;single&#39; &amp; &quot;double&quot;")))
