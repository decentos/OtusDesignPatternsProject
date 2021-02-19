GOBASE=$(shell pwd)
GOBIN=$(GOBASE)

build:
	go build -ldflags="-s -w" -o '$(GOBIN)/hw01' ./main.go || exit

run:
	go build -o '$(GOBIN)/hw01' ./main.go
	'$(GOBIN)/hw01'

test:
	go test -v -count=1 -race -timeout=60s ./...

install-deps:
	go install github.com/golangci/golangci-lint/cmd/golangci-lint
	GO111MODULE=on go get github.com/golang/mock/mockgen@latest

lint: install-deps
	golangci-lint run ./...

install:
	go mod download

generate:
	go generate ./...

.PHONY: build
