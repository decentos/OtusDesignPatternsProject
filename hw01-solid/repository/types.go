package repository

//go:generate mockgen -source=$GOFILE -destination ./mocks/mock_storager.go -package mocks Storager
// Интерфейс хранения данных.
type Storager interface {
	Get(key string) interface{}
	Set(key string, value interface{})
}
