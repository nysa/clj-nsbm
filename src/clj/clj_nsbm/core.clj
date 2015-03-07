(ns clj-nsbm.core
  (:require [clojure.string :as string]))

(declare item->str)
(declare subfolder)
(declare shortcut)

(def head
  (str "<!DOCTYPE NETSCAPE-Bookmark-file-1>"
       "<!--This is an automatically generated file. "
       "It will be read and overwritten. "
       "Do Not Edit! -->"
       "<Title>Bookmarks</Title>"
       "<H1>Bookmarks</H1>"
       "<DL>"))

(def foot "</DL>")

(defn- attr-str
  "Returns the string representation of a given vector as an HTML attribute
  key/value pair where the first item is the attribute name and the second
  item is the attribute value."
  [v]
  (str " " (name (first v)) "=\"" (str (second v)) "\""))

(defn- attrs-str
  "Returns the string representation of a given map as HTML attribute
  key/value pairs"
  [m]
  (->> m (map attr-str) string/join))

(defn item->attrs
  [item, keys-attrs]
  (->> (select-keys item (keys keys-attrs))
       (map #(list ((first %) keys-attrs) (last %))) flatten
       (apply hash-map)))

(defn item->str
  [item]
  (if (:url item) (shortcut item) (subfolder item)))

(defn subfolder
  [item]
  (let [keys-attrs {:date :ADD_DATE}]
    (str "<DT><H3 FOLDED"
         (attrs-str (item->attrs item keys-attrs)) ">"
         (:title item) "</H3><DL><p>"
         (string/join (map item->str (:children item)))
         "</DL><p>")))

(defn shortcut
  [item]
  (let [keys-attrs {:url :HREF, :date :ADD_DATE}]
    (str "<DT><A" (attrs-str (item->attrs item keys-attrs)) ">"
         (:title item) "</A>")))
