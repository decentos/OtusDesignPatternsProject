// Code generated by MockGen. DO NOT EDIT.
// Source: rotate.go

// Package mocks is a generated GoMock package.
package mocks

import (
	github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
	gomock "github.com/golang/mock/gomock"
	reflect "reflect"
)

// MockRotable is a mock of Rotable interface
type MockRotable struct {
	ctrl     *gomock.Controller
	recorder *MockRotableMockRecorder
}

// MockRotableMockRecorder is the mock recorder for MockRotable
type MockRotableMockRecorder struct {
	mock *MockRotable
}

// NewMockRotable creates a new mock instance
func NewMockRotable(ctrl *gomock.Controller) *MockRotable {
	mock := &MockRotable{ctrl: ctrl}
	mock.recorder = &MockRotableMockRecorder{mock}
	return mock
}

// EXPECT returns an object that allows the caller to indicate expected use
func (m *MockRotable) EXPECT() *MockRotableMockRecorder {
	return m.recorder
}

// SetDirection mocks base method
func (m *MockRotable) SetDirection(v *geometry.Vector) {
	m.ctrl.T.Helper()
	m.ctrl.Call(m, "SetDirection", v)
}

// SetDirection indicates an expected call of SetDirection
func (mr *MockRotableMockRecorder) SetDirection(v interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "SetDirection", reflect.TypeOf((*MockRotable)(nil).SetDirection), v)
}

// GetDirection mocks base method
func (m *MockRotable) GetDirection() *geometry.Vector {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "GetDirection")
	ret0, _ := ret[0].(*geometry.Vector)
	return ret0
}

// GetDirection indicates an expected call of GetDirection
func (mr *MockRotableMockRecorder) GetDirection() *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "GetDirection", reflect.TypeOf((*MockRotable)(nil).GetDirection))
}

// GetAngularVelocity mocks base method
func (m *MockRotable) GetAngularVelocity() *geometry.Vector {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "GetAngularVelocity")
	ret0, _ := ret[0].(*geometry.Vector)
	return ret0
}

// GetAngularVelocity indicates an expected call of GetAngularVelocity
func (mr *MockRotableMockRecorder) GetAngularVelocity() *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "GetAngularVelocity", reflect.TypeOf((*MockRotable)(nil).GetAngularVelocity))
}
