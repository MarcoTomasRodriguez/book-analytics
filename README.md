# Book Analytics

Book Analytics is a simple program written in Scala that extracts the words from multiples PDFs and ranks them by their 
count.

## Usage

```bash
book-analytics output [input]
```

**Output**: filename ended in {.csv\|.json}

**Input**: list of files ended in {.pdf}

## Examples

```bash
book-analytics /path/to/output.json /path/to/input.pdf
```

```bash
book-analytics /path/to/output.csv /path/to/input_1.pdf /path/to/input_n.pdf
```
