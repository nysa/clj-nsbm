(ns clj-nsbm.util
  (:require [clojure.string :as string]
            [clojure.set :refer [rename-keys]]))

(defn attr-str
  "Returns the string representation of a given vector as an HTML attribute
  key/value pair where the first item is the attribute name and the second
  item is the attribute value."
  [v]
  (str " " (name (first v)) "=\"" (str (second v)) "\""))

(defn attrs-str
  "Returns the string representation of a given map as HTML attribute
  key/value pairs."
  [m]
  (->> m (map attr-str) string/join))

(defn item-attrs
  "Returns a map containing only those entries in m whose key is in kmap,
  with the keys in kmap renamed to the values in kmap."
  [m, kmap]
  (-> m (select-keys (keys kmap)) (rename-keys kmap)))

(defn escape-html
  "Returns a string with HTML syntax characters in s escaped.
  See http://www.w3.org/International/questions/qa-escapes#use"
  [s]
  (string/escape s {\< "&lt;", \> "&gt;", \& "&amp;", \" "&quot;", \' "&#39;"}))
