# OrderScheduler

    Assumptions :
    -   The drone operates from 6-10 PM, it can leave anytime before 10PM
    -   There is one operating drone
    -   Order Information is known only via a Server (it is not predetermined/futuristic)
    -   The time for order delivery is taken from the arrival time and not from the warehouse open time.
    -   Orders are coming in order of there arrival time.
    -   If an order arrives before 6 AM it is considered as promoter if it is processed till 7AM.
    -   A drone can carry one delivery at a time


    Design :
    -   Warehouse class (Base Class)
        - StrategyBasic -
            Works on giving priority to delayed orders
        - HeuristicStrategy -
            Works on giving priority to heuristic sum of scores considering distance and inverse of time spent as metric


    For Compiling and running code -
    ./run.sh
    put your input file inside src/com/example/orderscheduler
    output file will be in main directory
