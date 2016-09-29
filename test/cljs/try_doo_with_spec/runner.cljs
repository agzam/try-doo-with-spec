(ns try-doo-with-spec.runner
  (:require [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [try-doo-with-spec.foo-test]))
  
(doo-tests 'try-doo-with-spec.foo-test)

