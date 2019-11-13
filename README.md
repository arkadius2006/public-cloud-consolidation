# Specification

## Generation

Example:
```
./generate.sh --hosts 1000 --vms 3000 --output configuration.json
```

Result: file `configuration.json` contains cloud configuration for 1000 hosts and 3000 VMs.

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

For file formats description see below, please.

## Configuration file format
We use JSON to store cloud configuration.

Cloud configuration format example:
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
