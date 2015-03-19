(ns clj-nsbm.util-test
  #+cljs (:require-macros [cemerick.cljs.test :refer [deftest is testing]])
  (:require [clj-nsbm.util :as util]
            #+clj [clojure.test :refer [deftest is testing]]
            #+cljs [cemerick.cljs.test :as t]))

(deftest attr-str-test
  (is (= (util/attr-str ["href" "http://clojure.org"]) " href=\"http://clojure.org\""))
  (is (= (util/attr-str [:href  "http://clojure.org"]) " href=\"http://clojure.org\"")))

(deftest attrs-str-test
  (is (= (util/attrs-str {:title "Clojure"}) " title=\"Clojure\""))
  (is (= (util/attrs-str {:title "Clojure" :href "http://clojure.org"}) " title=\"Clojure\" href=\"http://clojure.org\"")))

(deftest item-attrs-test
  (is (= (util/item-attrs {:url "http://clojure.org"} {:url :HREF}) {:HREF "http://clojure.org"}))
  (is (= (util/item-attrs {:url "http://clojure.org"} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org"}))
  (is (= (util/item-attrs {:url "http://clojure.org" :date 0} {:url :HREF, :date :ADD_DATE}) {:HREF "http://clojure.org" :ADD_DATE 0})))

(deftest escape-html-test
  (is (= (util/escape-html "zero < one") "zero &lt; one"))
  (is (= (util/escape-html "two > one") "two &gt; one"))
  (is (= (util/escape-html "'single' & \"double\"") "&#39;single&#39; &amp; &quot;double&quot;")))
