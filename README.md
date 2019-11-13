# Specification

## Generation

Example:
```
./generate.sh --hosts 1000 --vms 3000 --output configuration.json
```

Result: file `configuration.json` contains cloud configuration for 1000 hosts and 3000 VMs. Configuration file format is described below.

## Consolidation
Example:
```
./consolidate.sh --input input.json --output.json --metrics metrics.txt
```

| Parameter | Description |
| --------- | ----------- |
| input.json | File with initial configuration |
| output.json | File where to write consolidated configuration |
| metrics.txt | File with consolidation routine metrics |

Configuration and metrics file formats are described below.

## Configuration file format
We use JSON to store cloud configuration. Cloud configuration format example:
```
{
    "hosts": [
        {
            "id": "host-1",
            "cpu": 8,
            "ram": 64
        },
        {
            "id": "host-2",
            "cpu": "2",
            "ram": "8"
        }
    ],

    "vms": [
        {
            "id": "vm-1",
            "cpu": "1",
            "ram": 2
        },
        {
            "id": "vm-2",
            "cpu": 4,
            "ram": 32
        },
        {
            "id": "vm-3",
            "cpu": 2,
            "ram": 8
        }
    ],

    "layout": [
        {
            "vm": "vm-1",
            "host": "host-1",
        },
        {
            "vm": "vm-2",
            "host": "host-1",
        },
        {
            "vm": "vm-3",
            "host": "host-2"
        }
    ]
}
```

## Metrics file format
Metrics file contains one or more lines. Each line contains fields: algorithm name, input file name, run time (ms) and consolidated number of hosts. Fields are tab-separated.

Metrics file example:
```
algo_arkady_1   conf_1000h.json 10000   900
algo_andrei_1   conf_1000h.json 8000    950
algo_arkady_2   conf_1000h.json 9000    850
```
This format (tab-separated, rather than json) makes it easy to use awk and other Unix command line tools to process results and convert to other formats, if necessary.
