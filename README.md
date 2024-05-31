# Simple web calculator
## Overview
This web calculator can summate and subtract integer values, also it has memory where all the operations performed are recorded.
## Features
- Summation of integer values (*address*/plus/{value a}/{value b});
- Subtraction of integer values (*address*/minus/{diminutive}/{deductible});
- Provide memory from all expressions that have been counted (*address*/plus or *address*/minus or *address*/expressions).
> All the info is provided via JSON objects. To get the result of summation or subtraction you should get the value of provided object's "result" property .

## API documentation
API has 5 functional endpoints:
- Summation of integer values (*address*/plus/{value a}/{value b}) - reuturns an JSON object with expression as string and result as integer, or an error message;
![Plus values](/img/plus.png?raw=true "screenshot from UI API documentation")
- Subtraction of integer values (*address*/minus/{diminutive}/{deductible}) - reuturns an JSON object with expression as string and result as integer, or an error message;
![Minus values](/img/minus.png?raw=true "screenshot from UI API documentation")
- Provide memory from all expressions that have been counted (*address*/expressions) - reuturns an JSON object with list of expression objects;
![All values](/img/all.png?raw=true "screenshot from UI API documentation")
-  Provide memory from all expressions that have been counted with summation (*address*/plus) - reuturns an JSON object with list of expression objects;
![All plus values](/img/plusMemory.png?raw=true "screenshot from UI API documentation")
-  Provide memory from all expressions that have been counted with subtraction (*address*/minus) - reuturns an JSON object with list of expression objects;
![All minus values](/img/minusMemory.png?raw=true "screenshot from UI API documentation")

## API documentation endpoints
- To get API UI documentation visit *address*/calculator-info-ui;
- To get API-docs visit *address*/calculator-api-docs.
