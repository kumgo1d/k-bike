package com.goldcompany.apps.koreabike.db.history_address;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserHistoryAddressDAO_Impl implements UserHistoryAddressDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserHistoryAddress> __insertionAdapterOfUserHistoryAddress;

  private final EntityDeletionOrUpdateAdapter<UserHistoryAddress> __deletionAdapterOfUserHistoryAddress;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAddressUnselect;

  public UserHistoryAddressDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserHistoryAddress = new EntityInsertionAdapter<UserHistoryAddress>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user_address` (`date`,`latitude`,`longitude`,`address`,`keyword`,`selected`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserHistoryAddress value) {
        stmt.bindLong(1, value.getDate());
        stmt.bindDouble(2, value.getLatitude());
        stmt.bindDouble(3, value.getLongitude());
        if (value.getAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAddress());
        }
        if (value.getKeyword() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getKeyword());
        }
        final int _tmp;
        _tmp = value.getSelected() ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfUserHistoryAddress = new EntityDeletionOrUpdateAdapter<UserHistoryAddress>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `user_address` WHERE `latitude` = ? AND `longitude` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserHistoryAddress value) {
        stmt.bindDouble(1, value.getLatitude());
        stmt.bindDouble(2, value.getLongitude());
      }
    };
    this.__preparedStmtOfUpdateAddressUnselect = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_address SET selected = 0 WHERE date = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final UserHistoryAddress item,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserHistoryAddress.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final UserHistoryAddress item,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserHistoryAddress.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateAddressUnselect(final long date,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAddressUnselect.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, date);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateAddressUnselect.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAll(final Continuation<? super List<UserHistoryAddress>> continuation) {
    final String _sql = "select * from user_address ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserHistoryAddress>>() {
      @Override
      public List<UserHistoryAddress> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
          final int _cursorIndexOfSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "selected");
          final List<UserHistoryAddress> _result = new ArrayList<UserHistoryAddress>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserHistoryAddress _item;
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpKeyword;
            if (_cursor.isNull(_cursorIndexOfKeyword)) {
              _tmpKeyword = null;
            } else {
              _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
            }
            final boolean _tmpSelected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSelected);
            _tmpSelected = _tmp != 0;
            _item = new UserHistoryAddress(_tmpDate,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpKeyword,_tmpSelected);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAddress(final Continuation<? super UserHistoryAddress> continuation) {
    final String _sql = "select * from user_address WHERE selected = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserHistoryAddress>() {
      @Override
      public UserHistoryAddress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
          final int _cursorIndexOfSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "selected");
          final UserHistoryAddress _result;
          if(_cursor.moveToFirst()) {
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpKeyword;
            if (_cursor.isNull(_cursorIndexOfKeyword)) {
              _tmpKeyword = null;
            } else {
              _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
            }
            final boolean _tmpSelected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSelected);
            _tmpSelected = _tmp != 0;
            _result = new UserHistoryAddress(_tmpDate,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpKeyword,_tmpSelected);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
