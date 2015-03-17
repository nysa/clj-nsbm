(ns clj-nsbm.core
  "Public API for building Netscape Bookmark documents. For specs see
  https://msdn.microsoft.com/en-us/library/aa753582%28v=vs.85%29.aspx"
  (:require [clojure.string :as string]
            [clj-nsbm.util :as util]))

(declare build-item)
(declare build-subfolder)
(declare build-shortcut)

(defn build
  "Given a vector, returns the entire Netscape Bookmark document compiled as a
  string."
  [v]
  (str "<!DOCTYPE NETSCAPE-Bookmark-file-1>"
       "<!--This is an automatically generated file. "
       "It will be read and overwritten. "
       "Do Not Edit! -->"
       "<Title>Bookmarks</Title>"
       "<H1>Bookmarks</H1>"
       "<DL>" (->> v (map build-item) string/join) "</DL>"))

(defn build-item
  "Given a map, returns a string representing either a subfolder or shortcut.
  The map is represented as a shortcut if the :url key exists and a subfolder
  otherwise."
  [m]
  (if (:url m) (build-shortcut m) (build-subfolder m)))

(defn build-subfolder
  "Given a map, returns a string representing a subfolder."
  [m]
  (let [kmap {:date :ADD_DATE}]
    (str "<DT>"
         "<H3 FOLDED" (-> m (util/item-attrs kmap) util/attrs-str) ">"
         (util/escape-html (:title m ""))
         "</H3>"
         "<DL><p>"
         (string/join (map build-item (:children m)))
         "</DL><p>")))

(defn build-shortcut
  "Given a map, returns a string representing a shortcut."
  [m]
  (let [kmap {:url :HREF
              :date :ADD_DATE
              :modified :LAST_MODIFIED
              :visited :LAST_VISIT}]
    (str "<DT>"
         "<A" (-> m (util/item-attrs kmap) util/attrs-str) ">"
         (util/escape-html (:title m ""))
         "</A>")))
