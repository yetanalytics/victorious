# victorious

A tiny little wrapper for [Formidable Labs' Victory](https://formidable.com/open-source/victory/) charting library for React + Reagent.

## Overview

`victorious` is intended to be easy to call and use in a way that feels familiar to Reagent users. The Victory component API is wrapped and presented in Cljs as kebab-cased component functions. For example, `Victory.VictoryPie` -> `com.yetanalytics.victorious/pie`. Here's a pie chart in Clojurescript:

    (ns ...
      (:require
        [reagent.core :as r]
        [com.yetanalytics.victorious :as v] ...))

    ...

    [v/pie {:color-scale ["tomato" "orange" "gold" "cyan" "navy"]
            :data [{:x "Cats" :y 30} {:x "Dogs" :y 70}]}]

    ...

Dynamic data is super easy, as are animations:

    (def pie-data (r/atom [{:x "Cats" :y 30} {:x "Dogs" :y 70}]))
    ...
    [v/pie {:color-scale ["tomato" "orange" "gold" "cyan" "navy"]
            :animate {:duration 2000
                      :easing "bounce"}
            :data @pie-data}]

And composition works just like it does in vanilla JSX victory:

    [v/chart {...props}
      [v/line {...props}]]

## Development

To get an interactive development environment run:

    clojure -A:fig:build

## License

Copyright © 2019 Yet Analytics Inc.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
