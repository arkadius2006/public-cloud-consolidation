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
