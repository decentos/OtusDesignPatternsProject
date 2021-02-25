package adapters //nolint:dupl

import (
	"testing"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/actions"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/repository/mocks"
	"github.com/golang/mock/gomock"
	"github.com/stretchr/testify/require"
)

func TestNewMovable(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)

		movable, err := NewMovable(storage)
		require.NotNil(t, movable)
		require.NoError(t, err)
	})
	t.Run("check errors", func(t *testing.T) {
		t.Run("storage is nil", func(t *testing.T) {
			movable, err := NewMovable(nil)
			require.Nil(t, movable)
			require.EqualError(t, ErrStorageNil, err.Error())
		})
	})
}

func TestSetPosition(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		v := geometry.Vector{X: 1, Y: 2}

		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Set(UnitPosition, &v).Return()

		movable, _ := NewMovable(storage)
		movable.SetPosition(&v)
	})
	t.Run("check nil vector", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Set(UnitPosition, nil).Return()

		movable, _ := NewMovable(storage)
		movable.SetPosition(nil)
	})
}

func TestGetPosition(t *testing.T) {
	testMovableGetters(t, UnitPosition, func(m actions.Movable) func() *geometry.Vector {
		return m.GetPosition
	})
}

func TestGetVelocity(t *testing.T) {
	testMovableGetters(t, UnitVelocity, func(m actions.Movable) func() *geometry.Vector {
		return m.GetVelocity
	})
}

func testMovableGetters(t *testing.T, key string, fn func(m actions.Movable) func() *geometry.Vector) {
	t.Run("check normal state", func(t *testing.T) {
		v := geometry.Vector{X: 1, Y: 2}

		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Get(key).Return(&v)

		movable, _ := NewMovable(storage)
		res := fn(movable)()
		require.EqualValues(t, v, *res)
	})
	t.Run("check nil", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		storage := mocks.NewMockStorager(ctrl)
		storage.EXPECT().Get(key).Return(nil)

		movable, _ := NewMovable(storage)
		res := fn(movable)()
		require.Nil(t, res)
	})
}
