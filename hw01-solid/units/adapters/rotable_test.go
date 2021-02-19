package adapters //nolint:dupl

import (
	"testing"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/actions"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/repository/mocks"
	"github.com/golang/mock/gomock"
	"github.com/stretchr/testify/require"
)

func TestNewRotable(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)

		rotable, err := NewRotable(storage)
		require.NotNil(t, rotable)
		require.NoError(t, err)
	})
	t.Run("check errors", func(t *testing.T) {
		t.Run("storage is nil", func(t *testing.T) {
			rotable, err := NewRotable(nil)
			require.Nil(t, rotable)
			require.EqualError(t, ErrStorageNil, err.Error())
		})
	})
}

func TestSetDirection(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		v := geometry.Vector{X: 1, Y: 2}

		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Set(UnitDirection, &v).Return()

		rotable, _ := NewRotable(storage)
		rotable.SetDirection(&v)
	})
	t.Run("check nil vector", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Set(UnitDirection, nil).Return()

		rotable, _ := NewRotable(storage)
		rotable.SetDirection(nil)
	})
}

func TestGetDirection(t *testing.T) {
	testRotableGetters(t, UnitDirection, func(m actions.Rotable) func() *geometry.Vector {
		return m.GetDirection
	})
}

func TestGetAngularVelocity(t *testing.T) {
	testRotableGetters(t, UnitAngularVelocity, func(m actions.Rotable) func() *geometry.Vector {
		return m.GetAngularVelocity
	})
}

func testRotableGetters(t *testing.T, key string, fn func(m actions.Rotable) func() *geometry.Vector) {
	t.Run("check normal state", func(t *testing.T) {
		v := geometry.Vector{X: 1, Y: 2}

		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Get(key).Return(&v)

		rotable, _ := NewRotable(storage)
		res := fn(rotable)()
		require.EqualValues(t, v, *res)
	})
	t.Run("check nil", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Get(key).Return(nil)

		rotable, _ := NewRotable(storage)
		res := fn(rotable)()
		require.Nil(t, res)
	})
}
