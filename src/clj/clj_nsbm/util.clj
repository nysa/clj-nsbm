(ns clj-nsbm.util
  (:require [clojure.string :as string]))

(defn attr-str
  "Returns the string representation of a given vector as an HTML attribute
  key/value pair where the first item is the attribute name and the second
  item is the attribute value."
  [v]
  (str " " (name (first v)) "=\"" (str (second v)) "\""))

(defn attrs-str
  "Returns the string representation of a given map as HTML attribute
  key/value pairs"
  [m]
  (->> m (map attr-str) string/join))

(defn item->attrs
  [item, keys-attrs]
  (->> (select-keys item (keys keys-attrs))
       (map #(list ((first %) keys-attrs) (last %))) flatten
       (apply hash-map)))
